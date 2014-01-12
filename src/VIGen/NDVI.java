/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package VIGen;

/**
 *
 * @author RaunakS
 */

import java.awt.image.DataBuffer;
import java.awt.image.renderable.ParameterBlock;

import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;

/**
 * This class calculates and displays a NDVI (Normalized Difference Vegetation Index)
 * image from two Landsat bands.
 */
public class NDVI
  {
 /**
  * The application entry point. We need to provide two input images.
  */
  public NDVI(String RED, String NearIR)
    {
        // First we open the input images. We assume that each band is in a separate file.
        // We assume that the first image is a near infrared one and that the second is
        // a visible red image -- if you're using Landsat images, those will be bands
        // 4 and 3, respectively.
    //    String nir = "G:\\M. Tech\\Mini Project\\liss3_poanta_feb_nir.tif";
    //    String r = "G:\\M. Tech\\Mini Project\\liss3_poanta_feb_r.tif";
        PlanarImage iNIR = JAI.create("fileload",NearIR);
        PlanarImage iVIS = JAI.create("fileload",RED);
        // The pixels on those images must be processed as floating-point values!
        ParameterBlock pbConvert = new ParameterBlock();
        pbConvert.addSource(iNIR);
        pbConvert.add(DataBuffer.TYPE_DOUBLE);
        PlanarImage NIR = JAI.create("format", pbConvert);
        pbConvert = new ParameterBlock();
        pbConvert.addSource(iVIS);
        pbConvert.add(DataBuffer.TYPE_DOUBLE);
        PlanarImage VIS = JAI.create("format", pbConvert);    
        // Calculate the denominator (NIR+VIS).
        ParameterBlock pbd = new ParameterBlock();
        pbd.addSource(NIR);
        pbd.addSource(VIS);
        PlanarImage denominator = JAI.create("add",pbd);
        // Calculate the numerator (NIR-VIS).
        ParameterBlock pbn = new ParameterBlock();
        pbn.addSource(NIR);
        pbn.addSource(VIS);
        PlanarImage numerator = JAI.create("subtract",pbn);
        // Calculate the NDVI.
        ParameterBlock pbNDVI = new ParameterBlock();
        pbNDVI.addSource(numerator);
        pbNDVI.addSource(denominator);
        final PlanarImage ndvi = JAI.create("divide",pbNDVI);
        // Create a GUI to show it.
        
        new ImageDisplayer(ndvi,"NDVI",true);
        new ImageDisplayer(ndvi, "EVI Frame");

    }
  
  public NDVI(String multiB, int sensor)
    {
        // First we open the input images. We assume that each band is in a separate file.
        // We assume that the first image is a near infrared one and that the second is
        // a visible red image -- if you're using Landsat images, those will be bands
        // 4 and 3, respectively.
//        String nir = "G:\\M. Tech\\Mini Project\\liss3_poanta_feb_nir.tif";
//        String r = "G:\\M. Tech\\Mini Project\\liss3_poanta_feb_r.tif";
    //    Raster mbImage = JAI.create("fileload", multiB);
        PlanarImage mImg = JAI.create("fileload",multiB);

        int[] bandIndicesB = new int[1];
        int[] bandIndicesG = new int[1];
        int[] bandIndicesR = new int[1];
        int[] bandIndicesNIR = new int[1];
        int[] bandIndicesMIR = new int[1];

        if(sensor == 3) {
            bandIndicesG[0] = 0; // choose the green band
            bandIndicesR[0] = 1; // choose the red band
            bandIndicesNIR[0] = 2; // choose the NIR band
            bandIndicesMIR[0] = 3; // choose the MIR band

            ParameterBlock paramsMIR = new ParameterBlock();
            paramsMIR.addSource(mImg);
            paramsMIR.add(bandIndicesMIR);
        }

        else if(sensor == 2) {
            bandIndicesB[0] = 0; // choose the blue band
            bandIndicesG[0] = 1; // choose the green band
            bandIndicesR[0] = 2; // choose the red band
            bandIndicesNIR[0] = 3; // choose the NIR band

            ParameterBlock paramsB = new ParameterBlock();
            paramsB.addSource(mImg);
            paramsB.add(bandIndicesB);
        }

        // Stores the required input source, and parameters in a
        // ParameterBlock (one for each color band) ...



        ParameterBlock paramsG = new ParameterBlock();
        paramsG.addSource(mImg);
        paramsG.add(bandIndicesG);

        ParameterBlock paramsR = new ParameterBlock();
        paramsR.addSource(mImg);
        paramsR.add(bandIndicesR);

        ParameterBlock paramsNIR = new ParameterBlock();
        paramsNIR.addSource(mImg);
        paramsNIR.add(bandIndicesNIR);



        PlanarImage iNIR = JAI.create("bandselect",paramsNIR);
        PlanarImage iVIS = JAI.create("bandselect",paramsR);

    //    PlanarImage iNIR = JAI.create("fileload",nir);
    //    PlanarImage iVIS = JAI.create("fileload",r);
        // The pixels on those images must be processed as floating-point values!
        ParameterBlock pbConvert = new ParameterBlock();
        pbConvert.addSource(iNIR);
        pbConvert.add(DataBuffer.TYPE_DOUBLE);
        PlanarImage NIR = JAI.create("format", pbConvert);
        pbConvert = new ParameterBlock();
        pbConvert.addSource(iVIS);
        pbConvert.add(DataBuffer.TYPE_DOUBLE);
        PlanarImage VIS = JAI.create("format", pbConvert);    
        // Calculate the denominator (NIR+VIS).
        ParameterBlock pbd = new ParameterBlock();
        pbd.addSource(NIR);
        pbd.addSource(VIS);
        PlanarImage denominator = JAI.create("add",pbd);
        // Calculate the numerator (NIR-VIS).
        ParameterBlock pbn = new ParameterBlock();
        pbn.addSource(NIR);
        pbn.addSource(VIS);
        PlanarImage numerator = JAI.create("subtract",pbn);
        // Calculate the NDVI.
        ParameterBlock pbNDVI = new ParameterBlock();
        pbNDVI.addSource(numerator);
        pbNDVI.addSource(denominator);
        final PlanarImage ndvi = JAI.create("divide",pbNDVI);

        // Create a GUI to show it. 
        new ImageDisplayer(ndvi,"NDVI",true);
        new ImageDisplayer(ndvi, "EVI Frame");
    
    }
  
  }
