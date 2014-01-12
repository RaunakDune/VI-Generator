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
public class SAVI {
    
    public SAVI(String NIRim, String Rim, double Lval) {
        double L[] = new double[]{Lval};
        double Lp1[] = new double[]{Lval+1};
        
        PlanarImage iNIR = JAI.create("fileload",NIRim);
        PlanarImage iRED = JAI.create("fileload",Rim);
        
        ParameterBlock pbConvert = new ParameterBlock();
        pbConvert.addSource(iNIR);
        pbConvert.add(DataBuffer.TYPE_DOUBLE);
        PlanarImage NIR = JAI.create("format", pbConvert);
        
        pbConvert = new ParameterBlock();
        pbConvert.addSource(iRED);
        pbConvert.add(DataBuffer.TYPE_DOUBLE);
        PlanarImage RED = JAI.create("format", pbConvert);
        
        ParameterBlock pbn = new ParameterBlock();
        pbn.addSource(NIR);
        pbn.addSource(RED);
        PlanarImage numerator = JAI.create("subtract",pbn);
        
        ParameterBlock pbl_add = new ParameterBlock();
        pbl_add.addSource(RED);
        pbl_add.add(L);
        PlanarImage L_ADD = JAI.create("AddConst",pbl_add);
        
        ParameterBlock pbd = new ParameterBlock();
        pbd.addSource(NIR);
        pbd.addSource(L_ADD);
        PlanarImage denominator = JAI.create("add",pbd);
        
        ParameterBlock pbfrac = new ParameterBlock();
        pbfrac.addSource(numerator);
        pbfrac.addSource(denominator);
        PlanarImage FRAC = JAI.create("divide",pbfrac);
        
        ParameterBlock pbf = new ParameterBlock();
        pbf.addSource(FRAC);
        pbf.add(Lp1);
        final PlanarImage FINAL = JAI.create("MultiplyConst",pbf);
        
        String title;
        if (Lval == 0.16)
            title = "OSAVI Image: ";
        else
            title = "OSAVI Image: ";
        new ImageDisplayer(FINAL, title);
    }
    
    public SAVI(String multiB, double Lval, int sensor) {
        
        double L[] = new double[]{Lval};
        double Lp1[] = new double[]{Lval+1};
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
        }

        else if(sensor == 2) {
            bandIndicesB[0] = 0; // choose the blue band
            bandIndicesG[0] = 1; // choose the green band
            bandIndicesR[0] = 2; // choose the red band
            bandIndicesNIR[0] = 3; // choose the NIR band
        }
        
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
        
        ParameterBlock pbn = new ParameterBlock();
        pbn.addSource(NIR);
        pbn.addSource(RED);
        PlanarImage numerator = JAI.create("subtract",pbn);
        
        ParameterBlock pbl_add = new ParameterBlock();
        pbl_add.addSource(RED);
        pbl_add.add(L);
        PlanarImage L_ADD = JAI.create("AddConst",pbl_add);
        
        ParameterBlock pbd = new ParameterBlock();
        pbd.addSource(NIR);
        pbd.addSource(L_ADD);
        PlanarImage denominator = JAI.create("add",pbd);
        
        ParameterBlock pbfrac = new ParameterBlock();
        pbfrac.addSource(numerator);
        pbfrac.addSource(denominator);
        PlanarImage FRAC = JAI.create("divide",pbfrac);
        
        ParameterBlock pbf = new ParameterBlock();
        pbf.addSource(FRAC);
        pbf.add(Lp1);
        final PlanarImage FINAL = JAI.create("MultiplyConst",pbf);
        
        String title;
        if (Lval == 0.16)
            title = "OSAVI Image: ";
        else
            title = "OSAVI Image: ";
        new ImageDisplayer(FINAL, title);
    }
    
}
