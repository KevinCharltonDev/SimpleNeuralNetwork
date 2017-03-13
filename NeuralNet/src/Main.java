import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Main {

	public static void main(String[] args) throws Exception {
		double learnRate = .02;
		double costTotal = 0;
		double cost = 0;
		String costStr = "";
		ImageHandler imageH = new ImageHandler("data2.png");
		
		ArrayList<DataPoint> data = imageH.dataFromImage();
		
		UI ui = new UI();
		final JLabel pic = new JLabel(new ImageIcon(imageH.img));
		final JLabel pic2 = new JLabel(new ImageIcon(imageH.img2));
		final JLabel learn = new JLabel("Learning Rate: ", JLabel.CENTER);
		final JLabel error = new JLabel("Cost: ", JLabel.CENTER);
		ui.topAdd(pic);
		ui.topAdd(pic2);
		ui.botAdd(learn);
		ui.botAdd(error);
		ui.update();
		ui.update();

		Layer input = new Layer(2);
		Layer l1 = new Layer(14);
		Layer l2 = new Layer(14);
		Layer l3 = new Layer(14);
		Layer output = new Layer(1);
		Junction j1 = new Junction(input, l1);
		Junction j2 = new Junction(l1, l2);
		Junction j3 = new Junction(l2, l3);
		Junction j4 = new Junction(l3, output);

		for (int k = 0; k < 100000000; k++) {
			learnRate = 1/(Math.log10(k+8)*100);

			DataPoint d = data
					.get((int) Math.floor(Math.random() * data.size()));

			input.neurons.get(0).outValue = d.x;
			input.neurons.get(1).outValue = d.y;

			j1.calcIn();
			l1.calcOut();
			j2.calcIn();
			l2.calcOut();
			j3.calcIn();
			l3.calcOut();
			j4.calcIn();
			output.calcOut();

			output.neurons.get(0).error = output.neurons.get(0).outValue
					* (1 - output.neurons.get(0).outValue)
					* (d.label - output.neurons.get(0).outValue);
			
			costTotal += output.neurons.get(0).error;
			cost = costTotal/k;
			costStr = String.valueOf(cost*1000000);
			
			j4.calcErr(learnRate);
			j3.calcErr(learnRate);
			j2.calcErr(learnRate);
			j1.calcErr(learnRate);
			output.calcBias(learnRate);
			l3.calcBias(learnRate);
			l2.calcBias(learnRate);
			l1.calcBias(learnRate);

			//Updates the output image once every so many iterations
			if (k % 10000 == 0) {
				int imageDownscale = 4;
				int width = imageH.img2.getWidth();
				int height = imageH.img2.getHeight();
				for (int i = 0; i < width / imageDownscale; i++) {
					for (int j = 0; j < height / imageDownscale; j++) {
						l1.resetVals();
						l2.resetVals();
						l3.resetVals();
						output.resetVals();
						
						double x = (i * 1.0 / (width/imageDownscale)) - 1;
						double y = (j * 1.0 / (height/imageDownscale)) - 1;
						input.neurons.get(0).outValue = x;
						input.neurons.get(1).outValue = y;

						j1.calcIn();
						l1.calcOut();
						j2.calcIn();
						l2.calcOut();
						j3.calcIn();
						l3.calcOut();
						j4.calcIn();
						output.calcOut();

						int val = 0;
						int redval = 0;
						if (output.neurons.get(0).outValue > .51) {
							val = (int) (10 + ((output.neurons.get(0).outValue - .5) * 500));
						}
						if (output.neurons.get(0).outValue < .49) {
							redval = (int) (10 + ((.5 - output.neurons.get(0).outValue) * 500));
						}
						val = Math.min(255, val);
						redval = Math.min(255, redval);
						Color c = new Color(redval, 0, val);
						imageH.img2.setRGB(i, j, c.getRGB());
					}
				}
				BufferedImage bi = new BufferedImage(imageH.img2.getWidth(null),
						imageH.img2.getHeight(null), BufferedImage.TYPE_INT_ARGB);
				Graphics g = bi.createGraphics();
				g.drawImage(imageH.img2, 0, 0, 800, 800, null);
				pic2.setIcon(new ImageIcon(bi));
				String text = "Learning Rate: " + String.valueOf(Math.round(learnRate*100000)) + " Cycles(K): " + String.valueOf(k/1000);
				text = text.substring(0, text.length()-1) + "." + text.substring(text.length()-1, text.length());
				learn.setText(text);
				text = "Error: " + costStr;
				error.setText(text);
				ui.update();
			}

			l1.resetVals();
			l2.resetVals();
			l3.resetVals();
			output.resetVals();
		}
	}

}
