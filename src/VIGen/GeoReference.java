/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package VIGen;

import java.io.File;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.gdal.gdal.Dataset;
import org.gdal.gdal.GCP;
import org.gdal.gdal.gdal;
import org.gdal.gdalconst.gdalconst;

/**
 *
 * @author RaunakS
 */
public class GeoReference {
    
    public GeoReference(String file){
        Dataset poDataset = null;
        try
        {
            File f = new File(file);

            poDataset = (Dataset) gdal.Open(f.getAbsolutePath(),
                gdalconst.GA_ReadOnly);
            if (poDataset == null)
            {
                System.out.println("The image could not be read.");
            }
        }
        catch (Exception e)
        {
            System.err.println("Exception caught.");
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        double[] adfGeoTransform = new double[6];

        System.out.println("Driver: " + poDataset.GetDriver().GetDescription());

        System.out.println("Size is: " + poDataset.getRasterXSize() + "x"
            + poDataset.getRasterYSize() + "  bands:"
            + poDataset.getRasterCount());

        if (poDataset.GetProjectionRef() != null)
            System.out.println("Projection is `" + poDataset.GetProjectionRef()
                + "'");

        Hashtable dict = poDataset.GetMetadata_Dict("");
        Enumeration keys = dict.keys();
        System.out.println(dict.size() + " items of metadata found (via Hashtable dict):");
        while (keys.hasMoreElements())
        {
            String key = (String) keys.nextElement();
            System.out.println(" :" + key + ":==:" + dict.get(key) + ":");
        }

        Vector list = poDataset.GetMetadata_List("");
        Enumeration enumerate = list.elements();
        System.out.println(list.size() + " items of metadata found (via Vector list):");
        while (enumerate.hasMoreElements())
        {
            String s = (String) enumerate.nextElement();
            System.out.println(" " + s);
        }

        Vector GCPs = new Vector();
        poDataset.GetGCPs(GCPs);
        System.out.println("Got " + GCPs.size() + " GCPs");
        Enumeration e = GCPs.elements();
        while (e.hasMoreElements())
        {
            GCP gcp = (GCP) e.nextElement();
            System.out.println(" x:" + gcp.getGCPX() +
                " y:" + gcp.getGCPY() +
                " z:" + gcp.getGCPZ() +
                " pixel:" + gcp.getGCPPixel() +
                " line:" + gcp.getGCPLine() +
                " line:" + gcp.getInfo());
        }

        poDataset.GetGeoTransform(adfGeoTransform);
        {
            System.out.println("Origin = (" + adfGeoTransform[0] + ", "
                + adfGeoTransform[3] + ")");

            System.out.println("Pixel Size = (" + adfGeoTransform[1] + ", "
                + adfGeoTransform[5] + ")");
        }
    }

}
