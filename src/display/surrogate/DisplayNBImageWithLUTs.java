package display.surrogate;
import java.awt.RenderingHints;
import java.awt.image.ColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.ParameterBlock;
import java.util.HashMap;

import javax.media.jai.ImageLayout;
import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;

/**
 * This class extends the DisplayNBImage class to allow the setting of a false-color
 * LUT to the displayed image.
 */
public class DisplayNBImageWithLUTs extends DisplayNBImage
  {
 /**
  * The constructor for the class, which calls the constructor for its ancestral class.
  */
  public DisplayNBImageWithLUTs(RenderedImage image)
    {
    super(image);
    }

 /**
  * This method sets a look-up-table for the surrogate image, converting it to an image with a
  * color model.
  */
  public void setLUT(short[][] colors)
    {
    // Now we convert the color array (which data type is short) into three
    // separate byte arrays. We always assume that the LUT has 256 entries.
    byte[] reds = new byte[256];
    byte[] greens = new byte[256];
    byte[] blues = new byte[256];
    for(int i=0;i<256;i++)
      {
      reds[i] = (byte)colors[i][0];
      greens[i] = (byte)colors[i][1];
      blues[i] = (byte)colors[i][2];
      }
    // Create an IndexColorModel using the arrays above.
    ColorModel colorModel = new IndexColorModel(8,256,reds,greens,blues);
    // To change the color model of the surrogate image, we need to create a new
    // image layout based on the image, and change the layout's color model.
    ImageLayout layout = new ImageLayout(surrogateImage);
    layout.setColorModel(colorModel);
    // In order to change the image layout we need to set its rendering hints.
    HashMap<RenderingHints.Key,ImageLayout> map = new HashMap<RenderingHints.Key,ImageLayout>();
    map.put(JAI.KEY_IMAGE_LAYOUT,layout);
    RenderingHints hints = new RenderingHints(map);
    // Reformat the image using the above hints.
    ParameterBlock pb = new ParameterBlock();
    pb.addSource(surrogateImage);
    // We don't really want to change the original surrogate image...
    PlanarImage newSurrogateImage = JAI.create("format",pb,hints);
    // Set the new, LUT-applied image.
    set(newSurrogateImage);
    }

  }