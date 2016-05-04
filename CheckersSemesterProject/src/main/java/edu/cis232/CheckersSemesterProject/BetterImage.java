package edu.cis232.CheckersSemesterProject;

import java.net.MalformedURLException;
import java.net.URL;

import javafx.scene.image.Image;

public class BetterImage extends Image {

	public BetterImage(String filename)  {
		
		super(BetterImage.class.getResource(filename).toString());

	}
}
