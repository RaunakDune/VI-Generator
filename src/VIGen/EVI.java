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
public class EVI {
    
    public EVI(String img[], double param[]) {
        
        double[] G = new double[]{param[0]};
        double[] C1 = new double[]{param[1]};
        double[] C2 = new double[]{param[2]};
        double[] L = new double[]{param[3]};
        
        PlanarImage iNIR = JAI.create("fileload",img[0]);
        PlanarImage iRED = JAI.create("fileload",img[2]);
        PlanarImage iBLUE = JAI.create("fileload",img[2]);
        
        ParameterBlock pbConvert = new ParameterBlock();
        pbConvert.addSource(iNIR);
        pbConvert.add(DataBuffer.TYPE_DOUBLE);
        PlanarImage NIR = JAI.create("format", pbConvert);
        
        pbConvert = new ParameterBlock();
        pbConvert.addSource(iRED);
        pbConvert.add(DataBuffer.TYPE_DOUBLE);
        PlanarImage RED = JAI.create("format", pbConvert);
        
        pbConvert = new ParameterBlock();
        pbConvert.addSource(iBLUE);
        pbConvert.add(DataBuffer.TYPE_DOUBLE);
        PlanarImage BLUE = JAI.create("format", pbConvert);
        
        ParameterBlock pbn = new ParameterBlock();
        pbn.addSource(NIR);
        pbn.addSource(RED);
        PlanarImage numerator = JAI.create("subtract",pbn);
        
        ParameterBlock pbc1r = new ParameterBlock();
        pbc1r.addSource(RED);
        pbc1r.add(C1);
        PlanarImage C1R = JAI.create("MultiplyConst",pbc1r,null);
        
        ParameterBlock pbc2b = new ParameterBlock();
        pbc2b.addSource(BLUE);
        pbc2b.add(C2);
        PlanarImage C2B = JAI.create("MultiplyConst",pbc2b,null);
        
        ParameterBlock pbcsub = new ParameterBlock();
        pbcsub.addSource(C1R);
        pbcsub.addSource(C2B);
        PlanarImage CSUB = JAI.create("subtract",pbcsub);
        
        ParameterBlock pbl_add = new ParameterBlock();
        pbl_add.addSource(CSUB);
        pbl_add.add(L);
        PlanarImage L_ADD = JAI.create("AddConst",pbl_add,null);
        
        ParameterBlock pbd = new ParameterBlock();
        pbd.addSource(NIR);
        pbd.addSource(L_ADD);
        PlanarImage denominator = JAI.create("add",pbd);
        
        ParameterBlock pbfrac = new ParameterBlock();
        pbfrac.addSource(numerator);
        pbfrac.addSource(denominator);
        PlanarImage FRAC = JAI.create("divide",pbfrac);
        
        ParameterBlock pbfinal = new ParameterBlock();
        pbfinal.addSource(FRAC);
        pbfinal.add(G);
        final PlanarImage FINAL = JAI.create("MultiplyConst",pbfinal,null);
        
        new ImageDisplayer(FINAL, "EVI Frame");
    }
    
    public EVI(String multiB, double param[]) {
        
        double[] G = new double[]{param[0]};
        double[] C1 = new double[]{param[1]};
        double[] C2 = new double[]{param[2]};
        double[] L = new double[]{param[3]};
        
//        double C1 = param[1];
//        double C2 = param[2];
//        double L = param[3];
        
        PlanarImage mImg = JAI.create("fileload",multiB);
    
        int[] bandIndicesB = new int[1];
        int[] bandIndicesG = new int[1];
        int[] bandIndicesR = new int[1];
        int[] bandIndicesNIR = new int[1];


        bandIndicesB[0] = 0; // choose the blue band
        bandIndicesG[0] = 1; // choose the green band
        bandIndicesR[0] = 2; // choose the red band
        bandIndicesNIR[0] = 3; // choose the NIR band

        ParameterBlock paramsB = new ParameterBlock();
        paramsB.addSource(mImg);
        paramsB.add(bandIndicesB);

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
        PlanarImage iBLUE = JAI.create("bandselect",paramsB);
        
        ParameterBlock pbConvert = new ParameterBlock();
        pbConvert.addSource(iNIR);
        pbConvert.add(DataBuffer.TYPE_DOUBLE);
        PlanarImage NIR = JAI.create("format", pbConvert);
        
        pbConvert = new ParameterBlock();
        pbConvert.addSource(iRED);
        pbConvert.add(DataBuffer.TYPE_DOUBLE);
        PlanarImage RED = JAI.create("format", pbConvert);
        
        pbConvert = new ParameterBlock();
        pbConvert.addSource(iBLUE);
        pbConvert.add(DataBuffer.TYPE_DOUBLE);
        PlanarImage BLUE = JAI.create("format", pbConvert);
        
        ParameterBlock pbn = new ParameterBlock();
        pbn.addSource(NIR);
        pbn.addSource(RED);
        PlanarImage numerator = JAI.create("subtract",pbn);
        
        ParameterBlock pbc1r = new ParameterBlock();
        pbc1r.addSource(RED);
        pbc1r.add(C1);
        PlanarImage C1R = JAI.create("MultiplyConst",pbc1r,null);
        
        ParameterBlock pbc2b = new ParameterBlock();
        pbc2b.addSource(BLUE);
        pbc2b.add(C2);
        PlanarImage C2B = JAI.create("MultiplyConst",pbc2b,null);
        
        ParameterBlock pbcsub = new ParameterBlock();
        pbcsub.addSource(C1R);
        pbcsub.addSource(C2B);
        PlanarImage CSUB = JAI.create("subtract",pbcsub);
        
        ParameterBlock pbl_add = new ParameterBlock();
        pbl_add.addSource(CSUB);
        pbl_add.add(L);
        PlanarImage L_ADD = JAI.create("AddConst",pbl_add,null);
        
        ParameterBlock pbd = new ParameterBlock();
        pbd.addSource(NIR);
        pbd.addSource(L_ADD);
        PlanarImage denominator = JAI.create("add",pbd);
        
        ParameterBlock pbfrac = new ParameterBlock();
        pbfrac.addSource(numerator);
        pbfrac.addSource(denominator);
        PlanarImage FRAC = JAI.create("divide",pbfrac);
        
        ParameterBlock pbfinal = new ParameterBlock();
        pbfinal.addSource(FRAC);
        pbfinal.add(G);
        final PlanarImage FINAL = JAI.create("MultiplyConst",pbfinal,null);
        
        new ImageDisplayer(FINAL, "EVI Frame");
    }
    
}
