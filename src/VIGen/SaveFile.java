/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package VIGen;

import java.awt.image.DataBuffer;
import java.awt.image.renderable.ParameterBlock;
import java.io.File;
import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author RaunakS
 */
public class SaveFile {
    
    public SaveFile(PlanarImage pi) {
      
        ParameterBlock pbdisp = new ParameterBlock();
        pbdisp.addSource(pi);
        pbdisp.add(DataBuffer.TYPE_FLOAT);
        PlanarImage DISP = JAI.create("format", pbdisp);
        
        String Title = "Save";
        String Message = "<html><p align=center>"+"Do you wish to use save this image ?";
        
        int response = JOptionPane.showConfirmDialog(null, Message, Title,JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.NO_OPTION) {
            return;
        } 
        else if (response == JOptionPane.YES_OPTION) {
            
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File("."));
            chooser.setFileFilter(new CustomFilter());
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            chooser.showSaveDialog(null);
            
            
            File selectedPfile = chooser.getSelectedFile();
            String initfilepath = selectedPfile.getAbsolutePath();
            String filepath = "";
            
            if(initfilepath.endsWith(".tif") == false && initfilepath.endsWith(".tiff") == false)
                filepath = initfilepath.concat(".tif");
            else
                filepath = initfilepath;
            JAI.create("filestore",DISP,filepath,"TIFF");
            
            return;
 
        }
    }
    
}
