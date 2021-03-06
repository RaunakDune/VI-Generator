/*
 * Part of the Java Image Processing Cookbook, please see
 * http://www.lac.inpe.br/~rafael.santos/JIPCookbook.jsp
 * for information on usage and distribution.
 * Rafael Santos (rafael.santos@lac.inpe.br)
 */
package display.surrogate;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.DataBuffer;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.ParameterBlock;

import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.media.jai.RenderedOp;
import javax.media.jai.iterator.RandomIter;
import javax.media.jai.iterator.RandomIterFactory;

import com.sun.media.jai.widget.DisplayJAI;

/**
 * This class shows how to use the DisplayJAI class to display floating-point or integer (i.e., 
 * non-byte) images. We will read the non-byte image and use a surrogate byte image for the 
 * real display. 
 * The surrogate byte image will have its values normalized to be in the 0-255 interval. 
 * The original image will be used to get the data from it.
 * The algorithm to rescale the image pixels gray levels so they will fit on a byte is very simple, 
 * and probably won't work for all cases - I assume that the max and min values used for rescaling 
 * pixels are to be extracted from all bands, and each band will be adjusted by the same max 
 * and min values. In other words, the rescaling is not done band by band. Since I didn't had the
 * chance to work with multiband floating-point images, I cannot check whether this approach is 
 * better or worse than rescaling band by band.
 * We'll implement the MouseMotionListener interface to capture the mouse's
 * movement in order to allow the display the values of the pixel under the
 * pointer in a JLabel. The display itself must be done in other class.
 */
public class DisplayNBImage extends DisplayJAI implements MouseMotionListener
  {
  protected StringBuffer pixelInfo; // The pixel information (formatted as a StringBuffer).
  protected double[] pixelValues; // The pixel values (bands) as an array of doubles.
  protected RandomIter readIterator; // A RandomIter that allow us to get the data of a single pixel.
  protected PlanarImage surrogateImage; // The surrogate byte image
  protected int width,height; // The dimensions of the image
  protected double minValue,maxValue; // The range of the image values.

 /**
  * The constructor of the class, which creates the arrays and instances needed
  * to obtain the image data and registers the class to listen to mouse motion events.
  * @param image a RenderedImage for display
  */
  public DisplayNBImage(RenderedImage image)
    {
    // Create the iterator.
    readIterator = RandomIterFactory.create(image, null);
    // Get some data about the image.
    width = image.getWidth();
    height = image.getHeight();
    // Create an array to receive the pixels values with the appropriate number of bands
    pixelValues = new double[image.getSampleModel().getNumBands()];
    // Which are the max and min of the image? We need to know to create the
    // surrogate image. Let's use the extrema operator to get them.
    ParameterBlock pbMaxMin = new ParameterBlock();
    pbMaxMin.addSource(image);
    RenderedOp extrema = JAI.create("extrema", pbMaxMin);
    // Must get the extrema of all bands !
    double[] allMins = (double[])extrema.getProperty("minimum");
    double[] allMaxs = (double[])extrema.getProperty("maximum");
    minValue = allMins[0];
    maxValue = allMaxs[0];
    for(int v=1;v<allMins.length;v++)
      {
      if (allMins[v] < minValue) minValue = allMins[v];
      if (allMaxs[v] > maxValue) maxValue = allMaxs[v];
      }
    // Rescale the image with the parameters
    double[] subtractThis = new double[1]; subtractThis[0] = minValue;
    double[] multiplyBy   = new double[1]; multiplyBy[0]   = 255./(maxValue-minValue);
    // Now we can rescale the pixels' values:
    ParameterBlock pbSub = new ParameterBlock();
    pbSub.addSource(image);
    pbSub.add(subtractThis);
    surrogateImage = (PlanarImage)JAI.create("subtractconst",pbSub,null);    
    ParameterBlock pbMult = new ParameterBlock();
    pbMult.addSource(surrogateImage);
    pbMult.add(multiplyBy);
    surrogateImage = (PlanarImage)JAI.create("multiplyconst",pbMult,null);    
    // Let's convert the data type for displaying.
    ParameterBlock pbConvert = new ParameterBlock();
    pbConvert.addSource(surrogateImage);
    pbConvert.add(DataBuffer.TYPE_BYTE);
    surrogateImage = JAI.create("format", pbConvert);
    set(surrogateImage);
    // Create the StringBuffer instance for the pixel information.
    pixelInfo = new StringBuffer(50);
    // Registers the mouse motion listener.
    addMouseMotionListener(this);
    } // end constructor

 /**
  * This method does not do anything, it is here just to keep the
  * MouseMotionListener interface happy.
  * @param me the mouse event that caused the execution of this method.
  */
  @Override
  public void mouseDragged(MouseEvent e) { }

 /**
  * This method will be called when the mouse is moved over the image being displayed.
  * @param me the mouse event that caused the execution of this method.
  */
  @Override
  public void mouseMoved(MouseEvent me)  
    {
    pixelInfo.setLength(0); // clear the StringBuffer
    int x = me.getX();
    int y = me.getY();
    if ((x >= width) || (y >= height)) // Consider only pixels within image bounds.
      {
      pixelInfo.append("No data!");
      return;
      }
    pixelInfo.setLength(0); // Clear the StringBuffer
    pixelInfo.append("(Original image data) "+x+","+y+": ");
    readIterator.getPixel(x,y,pixelValues); // Read the pixel
    for(int b=0;b<pixelValues.length;b++)
      pixelInfo.append(pixelValues[b]+","); // Append to the StringBuffer
    pixelInfo = pixelInfo.deleteCharAt(pixelInfo.length()-1); // Erase comma
    } // end of method mouseMoved

 /**
  * This method returns the surrogate image.
  * @returns the surrogate image.
  */
  public PlanarImage getSurrogateImage()  
    {
    return surrogateImage;
    }

 /**
  * This method allows external classes access to the pixel info which was
  * obtained in the mouseMoved method.
  * @return the pixel information, formatted as a string
  */
  public String getPixelInfo()
    {
    return pixelInfo.toString();
    }

 /**
  * This method returns the minimum value of the image data.
  * @return the minimum data value of the image data.
  */
  public double getMinValue()
    {
    return minValue;
    }

 /**
  * This method returns the maximum value of the image data.
  * @return the maximum data value of the image data.
  */
  public double getMaxValue()
    {
    return maxValue;
    }

  }