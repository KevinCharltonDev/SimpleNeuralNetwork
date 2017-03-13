import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;


public class ImageHandler {
	BufferedImage img = null;
	BufferedImage img2 = null;
	ArrayList<DataPoint> data = new ArrayList<DataPoint>();

	public ImageHandler(String fileName){
		try {
			img = ImageIO.read(new File(fileName));
		} catch (IOException e) {
		}
		img2 = new BufferedImage(200, 200, 1);
		for (int i = 0; i < img2.getWidth(); i++) {
			for (int j = 0; j < img2.getHeight(); j++) {
				img2.setRGB(i, j, Color.white.getRGB());
			}
		}
	}
	
	public ArrayList<DataPoint> dataFromImage(){
		int width = img.getWidth();
		int height = img.getHeight();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (img.getRGB(i, j) != -1) {
					double l = 1;
					if (img.getRGB(i, j) == -1237980) {
						l = 0;
					}
					data.add(new DataPoint((i * 1.0 / width) - 1,
							(j * 1.0 / height) - 1, l));
				}
			}
		}
		return data;
	}
}
