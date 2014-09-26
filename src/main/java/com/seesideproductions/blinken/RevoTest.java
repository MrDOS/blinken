package com.seesideproductions.blinken;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.seesideproductions.blinken.controllers.MyDMXController;

public class RevoTest
{
    public static void main(String[] args) throws Exception
    {
        Stage stage = new Stage();

        MonochromeGridFixture revo = new MonochromeGridFixture(0, 128, 16, 16);
        revo.setMapping(new int[] {
                29, 28, 25, 24, 21, 20, 17, 16, 13, 12, 9, 8, 5, 4, 1, 0,
                31, 30, 27, 26, 23, 22, 19, 18, 15, 14, 11, 10, 7, 6, 3, 2,
                61, 60, 57, 56, 53, 52, 49, 48, 45, 44, 41, 40, 37, 36, 33, 32,
                63, 62, 59, 58, 55, 54, 51, 50, 47, 46, 43, 42, 39, 38, 35, 34,
                93, 92, 89, 88, 85, 84, 81, 80, 77, 76, 73, 72, 69, 68, 65, 64,
                95, 94, 91, 90, 87, 86, 83, 82, 79, 78, 75, 74, 71, 70, 67, 66,
                125, 124, 121, 120, 117, 116, 113, 112, 109, 108, 105, 104, 101, 100, 97, 96,
                127, 126, 123, 122, 119, 118, 115, 114, 111, 110, 107, 106, 103, 102, 99, 98,
                157, 156, 153, 152, 149, 148, 145, 144, 141, 140, 137, 136, 133, 132, 129, 128,
                159, 158, 155, 154, 151, 150, 147, 146, 143, 142, 139, 138, 135, 134, 131, 130,
                189, 188, 185, 184, 181, 180, 177, 176, 173, 172, 169, 168, 165, 164, 161, 160,
                191, 190, 187, 186, 183, 182, 179, 178, 175, 174, 171, 170, 167, 166, 163, 162,
                221, 220, 217, 216, 213, 212, 209, 208, 205, 204, 201, 200, 197, 196, 193, 192,
                223, 222, 219, 218, 215, 214, 211, 210, 207, 206, 203, 202, 199, 198, 195, 194,
                253, 252, 249, 248, 245, 244, 241, 240, 237, 236, 233, 232, 229, 228, 225, 224,
                255, 254, 251, 250, 247, 246, 243, 242, 239, 238, 235, 234, 231, 230, 227, 226
        });
        stage.add(revo);

        int i = 0;
        boolean[][] frames = new boolean[args.length][];
        for (String filename : args)
        {
            BufferedImage imageFrame = ImageIO.read(new File(filename));
            boolean[] frame = new boolean[256];
            for (int x = 0; x < 16; x++)
                for (int y = 0; y < 16; y++)
                    frame[y * 16 + x] = (imageFrame.getRGB(x, y) != 0xFFFFFFFF) ? true : false;
            frames[i++] = frame;
        }

        MyDMXController controller = new MyDMXController();
        controller.open();

        i = 0;
        while (true)
        {
            controller.set(stage.render());
            revo.setDisplayValues(frames[i++ % frames.length]);
            Thread.sleep(100);
        }

        // controller.close();
    }
}