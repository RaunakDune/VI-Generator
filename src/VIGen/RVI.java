/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package VIGen;

import java.awt.image.DataBuffer;
import java.awt.image.renderable.ParameterBlock;
import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;

/**
 *
 * @author RaunakS
 */
public class RVI {
    
    public RVI(String REDim, String NearIRim)
    {

        PlanarImage iNIR = JAI.create("fileload",NearIRim);
        PlanarImage iVIS = JAI.create("fileload",REDim);

        // The pixels on those images must be processed as floating-point values!
        ParameterBlock pbConvert = new ParameterBlock();
        pbConvert.addSource(iNIR);
        pbConvert.add(DataBuffer.TYPE_DOUBLE);
        PlanarImage NIR = JAI.create("format", pbConvert);
        pbConvert = new ParameterBlock();
        pbConvert.addSource(iVIS);
        pbConvert.add(DataBuffer.TYPE_DOUBLE);
        PlanarImage RED = JAI.create("format", pbConvert); 

        // Calculate the RVI.
        ParameterBlock pbRVI = new ParameterBlock();
        pbRVI.addSource(NIR);
        pbRVI.addSource(RED);
        final PlanarImage rvi = JAI.create("divide",pbRVI);
        // Create a GUI to show it. 

        // Get some information about the image
        new ImageDisplayer(rvi, "EVI Frame");
    }
  
    public RVI(String multiB, int sensor)
    {
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
        PlanarImage iRED = JAI.create("bandselect",paramsR);

        ParameterBlock pbConvert = new ParameterBlock();
        pbConvert.addSource(iNIR);
        pbConvert.add(DataBuffer.TYPE_DOUBLE);
        PlanarImage NIR = JAI.create("format", pbConvert);
        pbConvert = new ParameterBlock();
        pbConvert.addSource(iRED);
        pbConvert.add(DataBuffer.TYPE_DOUBLE);
        PlanarImage RED = JAI.create("format", pbConvert);


        // Calculate the RVI.
        ParameterBlock pbRVI = new ParameterBlock();
        pbRVI.addSource(NIR);
        pbRVI.addSource(RED);
        final PlanarImage rvi = JAI.create("divide",pbRVI);

        // Create a GUI to show it.
        new ImageDisplayer(rvi, "EVI Frame");
    }
  
}
