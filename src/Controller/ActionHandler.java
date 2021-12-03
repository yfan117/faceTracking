package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JRadioButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Driver.Driver;
import Utilities.FindDensity;

public class ActionHandler extends Driver implements ActionListener, ChangeListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(render.update))
		{
			if(render.bufferedImage != null)
			{
				update();
			}
		}
		else if(e.getSource().equals(render.toggleImageButton))
		{
			if(render.bufferedImage != null)
			{
				render.toggleImage = !render.toggleImage;
				update();
			}
			
		}
		else if(e.getSource().equals(render.toggleGrideButton))
		{
			if(render.bufferedImage != null)
			{
				render.toggleGride = !render.toggleGride;
				update();
			}
			
		}
		else if(e.getActionCommand().equals("Open"))
		{
			JFileChooser fileChooser = new JFileChooser(new File("resource/"));
			
			if(fileChooser.showOpenDialog(null) == 0)
			{
				String URL = fileChooser.getSelectedFile().getAbsolutePath();
				newImageSelected(URL);
			}
		}
	
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource().equals(render.contrastSlider))
		{
			render.contrastLabel.setText("Contrast Value: " +render.contrastSlider.getValue());
			render.contrastValue = render.contrastSlider.getValue();
			
		}
		else if(e.getSource().equals(render.scanSizeSlider))
		{
			render.scanSizeLabel.setText("Scann Size: " +render.scanSizeSlider.getValue());
			render.scanSize = render.scanSizeSlider.getValue();
			
		}
		else if(e.getSource().equals(render.countSlider))
		{
			render.countLabel.setText("Count Number: " +render.countSlider.getValue());
			FindDensity.count = render.countSlider.getValue();
			
		}
		
		
	}

}
