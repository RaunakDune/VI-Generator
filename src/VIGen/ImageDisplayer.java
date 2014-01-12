/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package VIGen;

import com.sun.media.jai.widget.DisplayJAI;
import display.surrogate.DisplayNBImageWithLUTs;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

/**
 *
 * @author RaunakS
 */
public class ImageDisplayer {
    public ImageDisplayer(String location, String title){
        PlanarImage disp = JAI.create("fileload",location);
        JFrame frame = new JFrame();
        
        String imageInfo = "Dimensions : "+disp.getWidth()+"x"+disp.getHeight()+" Location : "+location;
        frame.setTitle(title);
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        DisplayJAI dj = new DisplayJAI(disp);
        contentPane.add(new JScrollPane(dj),BorderLayout.CENTER);
        contentPane.add(new JLabel(imageInfo),BorderLayout.SOUTH);
        frame.pack(); 
        frame.setVisible(true); 
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    public ImageDisplayer(final PlanarImage disp, String title){
        JFrame frame = new JFrame();
        
        String imageInfo = "Dimensions: "+disp.getWidth()+"x"+disp.getHeight();
        frame.setTitle(title);
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        DisplayJAI dj = new DisplayJAI(disp);
        JButton button = new JButton("Save File");
            //Add action listener to button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
                new SaveFile(disp);
            }
        });
        contentPane.add(button,BorderLayout.NORTH);
        contentPane.add(new JScrollPane(dj),BorderLayout.CENTER);
        contentPane.add(new JLabel(imageInfo),BorderLayout.SOUTH);
        frame.pack(); // adjust the frame size.
        frame.setVisible(true); // show the frame.
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    public ImageDisplayer(PlanarImage image, String title, Boolean LUT){
        JFrame frame = new JFrame(title+" Image displayed with Look Up Table");
        DisplayNBImageWithLUTs disp = new DisplayNBImageWithLUTs(image);
        disp.setLUT(colors);
        String dispInfo = "Dimensions: "+disp.getWidth()+"x"+disp.getHeight();
        frame.add(new JScrollPane(disp));
        frame.add(new JLabel(dispInfo), BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
    }
    
      // This LUT maps values for a natural-looking NDVI image. The values were
      // interpolated from a color table from GRASS.
    
    private static final short[][] colors = 
    {
          {  5, 24, 82},  //   0
          {  5, 24, 82},  //   1
          {  5, 24, 82},  //   2
          {  5, 24, 82},  //   3
          {  5, 24, 82},  //   4
          {  5, 24, 82},  //   5
          {  5, 24, 82},  //   6
          {  5, 24, 82},  //   7
          {  5, 24, 82},  //   8
          {  5, 24, 82},  //   9
          {  5, 24, 82},  //  10
          {  5, 24, 82},  //  11
          {  5, 24, 82},  //  12
          {  5, 24, 82},  //  13
          {  5, 24, 82},  //  14
          {  5, 24, 82},  //  15
          {  5, 24, 82},  //  16
          {  5, 24, 82},  //  17
          {  5, 24, 82},  //  18
          {  5, 24, 82},  //  19
          {  5, 24, 82},  //  20
          {  5, 24, 82},  //  21
          {  5, 24, 82},  //  22
          {  5, 24, 82},  //  23
          {  5, 24, 82},  //  24
          {  5, 24, 82},  //  25
          {  5, 24, 82},  //  26
          {  5, 24, 82},  //  27
          {  5, 24, 82},  //  28
          {  5, 24, 82},  //  29
          {  5, 24, 82},  //  30
          {  5, 24, 82},  //  31
          {  5, 24, 82},  //  32
          {  5, 24, 82},  //  33
          {  5, 24, 82},  //  34
          {  5, 24, 82},  //  35
          {  5, 24, 82},  //  36
          {  5, 24, 82},  //  37
          {  5, 24, 82},  //  38
          {  5, 24, 82},  //  39
          {  5, 24, 82},  //  40
          {  5, 24, 82},  //  41
          {  5, 24, 82},  //  42
          {  5, 24, 82},  //  43
          {  5, 24, 82},  //  44
          {  5, 24, 82},  //  45
          {  5, 24, 82},  //  46
          {  5, 24, 82},  //  47
          {  5, 24, 82},  //  48
          {  5, 24, 82},  //  49
          {  5, 24, 82},  //  50
          {  5, 24, 82},  //  51
          {  5, 24, 82},  //  52
          {  5, 24, 82},  //  53
          {  5, 24, 82},  //  54
          {  5, 24, 82},  //  55
          {  5, 24, 82},  //  56
          {  5, 24, 82},  //  57
          {  5, 24, 82},  //  58
          {  5, 24, 82},  //  59
          {  5, 24, 82},  //  60
          {  5, 24, 82},  //  61
          {  5, 24, 82},  //  62
          {  5, 24, 82},  //  63
          {  5, 24, 82},  //  64
          {  5, 24, 82},  //  65
          {  5, 24, 82},  //  66
          {  5, 24, 82},  //  67
          {  5, 24, 82},  //  68
          {  5, 24, 82},  //  69
          {  5, 24, 82},  //  70
          {  5, 24, 82},  //  71
          {  5, 24, 82},  //  72
          {  5, 24, 82},  //  73
          {  5, 24, 82},  //  74
          {  5, 24, 82},  //  75
          {  5, 24, 82},  //  76
          {  5, 24, 82},  //  77
          {  5, 24, 82},  //  78
          {  5, 24, 82},  //  79
          {  5, 24, 82},  //  80
          {  5, 24, 82},  //  81
          {  5, 24, 82},  //  82
          {  5, 24, 82},  //  83
          {  5, 24, 82},  //  84
          {  5, 24, 82},  //  85
          {  5, 24, 82},  //  86
          {  5, 24, 82},  //  87
          {  5, 24, 82},  //  88
          {  5, 24, 82},  //  89
          { 17, 35, 90},  //  90
          { 33, 50,101},  //  91
          { 49, 65,113},  //  92
          { 66, 80,124},  //  93
          { 82, 95,135},  //  94
          { 98,110,147},  //  95
          {115,125,158},  //  96
          {131,141,169},  //  97
          {147,156,180},  //  98
          {164,171,192},  //  99
          {180,186,203},  // 100
          {196,201,214},  // 101
          {213,216,226},  // 102
          {229,231,237},  // 103
          {246,246,248},  // 104
          {255,255,255},  // 105
          {255,255,255},  // 106
          {255,255,255},  // 107
          {255,255,255},  // 108
          {255,255,255},  // 109
          {255,255,255},  // 110
          {255,255,255},  // 111
          {255,255,255},  // 112
          {255,255,255},  // 113
          {255,255,255},  // 114
          {255,255,255},  // 115
          {255,255,255},  // 116
          {255,255,255},  // 117
          {255,255,255},  // 118
          {255,255,255},  // 119
          {255,255,255},  // 120
          {255,255,255},  // 121
          {255,255,255},  // 122
          {255,255,255},  // 123
          {255,255,255},  // 124
          {255,255,255},  // 125
          {255,255,255},  // 126
          {255,255,255},  // 127
          {247,245,243},  // 128
          {231,227,219},  // 129
          {216,209,196},  // 130
          {205,195,177},  // 131
          {202,190,168},  // 132
          {200,184,159},  // 133
          {198,179,150},  // 134
          {195,174,142},  // 135
          {193,168,133},  // 136
          {191,163,124},  // 137
          {189,164,119},  // 138
          {187,166,115},  // 139
          {185,168,111},  // 140
          {183,169,106},  // 141
          {181,171,102},  // 142
          {179,173, 97},  // 143
          {176,175, 93},  // 144
          {171,177, 88},  // 145
          {166,179, 83},  // 146
          {160,179, 77},  // 147
          {154,176, 71},  // 148
          {148,172, 65},  // 149
          {144,170, 59},  // 150
          {147,174, 55},  // 151
          {150,177, 50},  // 152
          {153,180, 46},  // 153
          {156,184, 42},  // 154
          {159,187, 38},  // 155
          {162,190, 34},  // 156
          {165,194, 29},  // 157
          {160,192, 24},  // 158
          {152,189, 17},  // 159
          {145,187, 11},  // 160
          {138,184,  5},  // 161
          {134,182,  2},  // 162
          {132,181,  2},  // 163
          {130,180,  2},  // 164
          {129,179,  2},  // 165
          {127,178,  1},  // 166
          {125,177,  1},  // 167
          {124,176,  1},  // 168
          {122,175,  1},  // 169
          {120,174,  0},  // 170
          {116,172,  0},  // 171
          {111,169,  0},  // 172
          {106,166,  0},  // 173
          {101,163,  0},  // 174
          { 98,161,  0},  // 175
          { 96,160,  0},  // 176
          { 93,159,  0},  // 177
          { 90,157,  0},  // 178
          { 87,156,  0},  // 179
          { 85,154,  0},  // 180
          { 82,153,  0},  // 181
          { 79,151,  0},  // 182
          { 75,149,  0},  // 183
          { 67,145,  0},  // 184
          { 58,140,  0},  // 185
          { 50,136,  0},  // 186
          { 42,131,  0},  // 187
          { 38,130,  0},  // 188
          { 34,128,  0},  // 189
          { 30,126,  0},  // 190
          { 26,125,  0},  // 191
          { 22,123,  0},  // 192
          { 18,121,  0},  // 193
          { 14,120,  0},  // 194
          { 10,118,  0},  // 195
          {  6,116,  0},  // 196
          {  2,115,  0},  // 197
          {  0,113,  0},  // 198
          {  0,111,  0},  // 199
          {  0,109,  0},  // 200
          {  0,107,  0},  // 201
          {  0,105,  0},  // 202
          {  0,103,  0},  // 203
          {  0,102,  0},  // 204
          {  0,100,  0},  // 205
          {  0, 98,  0},  // 206
          {  0, 96,  0},  // 207
          {  0, 94,  0},  // 208
          {  0, 92,  0},  // 209
          {  0, 90,  0},  // 210
          {  0, 89,  0},  // 211
          {  0, 87,  0},  // 212
          {  0, 86,  0},  // 213
          {  0, 85,  0},  // 214
          {  0, 83,  0},  // 215
          {  0, 82,  0},  // 216
          {  0, 81,  0},  // 217
          {  0, 79,  0},  // 218
          {  0, 78,  0},  // 219
          {  0, 77,  0},  // 220
          {  0, 75,  0},  // 221
          {  0, 74,  0},  // 222
          {  0, 73,  0},  // 223
          {  0, 71,  0},  // 224
          {  0, 70,  0},  // 225
          {  0, 69,  0},  // 226
          {  0, 67,  0},  // 227
          {  0, 66,  0},  // 228
          {  0, 65,  0},  // 229
          {  0, 63,  0},  // 230
          {  0, 62,  0},  // 231
          {  0, 61,  0},  // 232
          {  0, 59,  0},  // 233
          {  0, 58,  0},  // 234
          {  0, 57,  0},  // 235
          {  0, 55,  0},  // 236
          {  0, 53,  0},  // 237
          {  0, 51,  0},  // 238
          {  0, 49,  0},  // 239
          {  0, 47,  0},  // 240
          {  0, 45,  0},  // 241
          {  0, 43,  0},  // 242
          {  0, 42,  0},  // 243
          {  0, 40,  0},  // 244
          {  0, 38,  0},  // 245
          {  0, 36,  0},  // 246
          {  0, 34,  0},  // 247
          {  0, 32,  0},  // 248
          {  0, 29,  0},  // 249
          {  0, 24,  0},  // 250
          {  0, 19,  0},  // 251
          {  0, 14,  0},  // 252
          {  0,  9,  0},  // 253
          {  0,  4,  0},  // 254
          {  0,  0,  0},  // 255
    };

}
