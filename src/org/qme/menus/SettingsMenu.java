package org.qme.menus;

import org.qme.logging.PreferencesFile;
import org.qme.main.GlobalState;
import static org.qme.main.Main.displayError;
import static org.qme.main.Main.tooltipString;
import org.qme.main.QApplication;
import static org.qme.util.GlobalConstants.SCREEN_WIDTH;
import static org.qme.util.GlobalConstants.SCROLL_SPEED;
import static org.qme.util.GlobalConstants.SQUASH_FACTOR;
import static org.qme.util.GlobalConstants.TOOLTIPS;

import java.util.Hashtable;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.qme.vis.ui.QButton;

public class SettingsMenu {
	
	public static void makeMenu(QApplication app) {
		
		// Get back from settings
		new QButton(app, SCREEN_WIDTH / 2, 400, "Back") {

			@Override
			public void mouseClickOff() {
				app.setState(GlobalState.ESCAPE_MENU);
			}
			
			@Override
			public GlobalState getActiveState() {
				return GlobalState.SETTINGS_MENU;				
			}
			
		};
		
		// Toggle tooltips
		new QButton(app, SCREEN_WIDTH / 2, 100, "Tooltips: " + tooltipString()) {

			@Override
			public void mouseClickOff() {
				try {
					if (PreferencesFile.getPreference("tooltips").equals("true")) {
						TOOLTIPS = false;
						PreferencesFile.setPreference("tooltips", "false");
						// update the text
						text = "Tooltips: " + tooltipString();
					} else {
						TOOLTIPS = true;
						PreferencesFile.setPreference("tooltips", "true");
						// update the text
						text = "Tooltips: " + tooltipString();
					}
				} catch (Exception e) {
					displayError("oh, no, not exception e", true);
				}
			}
			
			@Override
			public GlobalState getActiveState() {
				return GlobalState.SETTINGS_MENU;
			}
		};
		
		// Adjust squash
		new QButton(app, SCREEN_WIDTH / 2, 200, "Change Squash") {
			
			@Override
			public void mouseClickOff() {
				
				// Create a JOptionPane
				JOptionPane box = new JOptionPane();
				
				// Create our little slider (internal numbers are squash * 20)
				JSlider slider = null;
				try {
					slider = new JSlider(40, 60,
							/* default value read from prefs */ Integer.parseInt(PreferencesFile.getPreference("squash"))
					);
				} catch (Exception e) {
					displayError("Error reading from preferences file in squash adjustment", true);
				}
				
				slider.setMajorTickSpacing(5);
				slider.setMinorTickSpacing(1);
				slider.setPaintTicks(true);
				
				// Custom labels
				Hashtable<Integer, JLabel> labels = new Hashtable<Integer, JLabel>();
				labels.put(40, new JLabel("2"));
				labels.put(45, new JLabel("2.25"));
				labels.put(50, new JLabel("2.5"));
				labels.put(55, new JLabel("2.75"));
				labels.put(60, new JLabel("3"));
				
				slider.setLabelTable(labels);
				slider.setPaintLabels(true);
				
				slider.addChangeListener(
					new ChangeListener() {

						@Override
						public void stateChanged(ChangeEvent e) {
							
							JSlider src = (JSlider) (e.getSource());
							
							// Write the selected value
							if (!src.getValueIsAdjusting()) {
								try {
									PreferencesFile.setPreference("squash", Integer.toString(src.getValue()));
								} catch (Exception e1) {
									e1.printStackTrace();
									displayError("Error writing to preferences file in squash adjustment", true);
								}
							}
							
							SQUASH_FACTOR = src.getValue() / 20f;
							
						}
						
					}
				);
				
				box.setMessage(new Object[] {"Set squash factor: ", slider});
				
			    box.setMessageType(JOptionPane.QUESTION_MESSAGE);
			    box.setOptionType(JOptionPane.OK_CANCEL_OPTION);
			    JDialog dialog = box.createDialog(app.qiscreen, "Squash Factor");
			    dialog.setVisible(true);
			    
				
			}
			
			@Override
			public GlobalState getActiveState() {
				return GlobalState.SETTINGS_MENU;
			}
			
		};
		
		// Scroll speed! WHEEEE!
		new QButton(app, SCREEN_WIDTH / 2, 300, "Scroll speed") {

			@Override
			public void mouseClickOff() {
				
				// Create a JOptionPane
				JOptionPane box = new JOptionPane();
				
				// Create our little slider (internal numbers are squash * 20)
				JSlider slider = null;
				try {
					slider = new JSlider(15, 35,
							/* default value read from prefs */ Integer.parseInt(PreferencesFile.getPreference("scroll_speed"))
					);
				} catch (Exception e) {
					displayError("Error reading from preferences file in scroll speed adjustment", true);
				}
				
				slider.setMajorTickSpacing(5);
				slider.setMinorTickSpacing(1);
				slider.setPaintTicks(true);
				
				// Custom labels
				Hashtable<Integer, JLabel> labels = new Hashtable<Integer, JLabel>();
				labels.put(15, new JLabel("1.5"));
				labels.put(20, new JLabel("2"));
				labels.put(25, new JLabel("2.5"));
				labels.put(30, new JLabel("3"));
				labels.put(35, new JLabel("3.5"));
				
				slider.setLabelTable(labels);
				slider.setPaintLabels(true);
				
				slider.addChangeListener(
					new ChangeListener() {

						@Override
						public void stateChanged(ChangeEvent e) {
							
							JSlider src = (JSlider) (e.getSource());
							
							// Write the selected value
							if (!src.getValueIsAdjusting()) {
								try {
									PreferencesFile.setPreference("scroll_speed", Integer.toString(src.getValue()));
								} catch (Exception e1) {
									e1.printStackTrace();
									displayError("Error writing to preferences file in scroll speed adjustment", true);
								}
							}
							
							SCROLL_SPEED = src.getValue();
							
						}
						
					}
				);
				
				box.setMessage(new Object[] {"Set scroll speed: ", slider});
				
			    box.setMessageType(JOptionPane.QUESTION_MESSAGE);
			    box.setOptionType(JOptionPane.OK_CANCEL_OPTION);
			    JDialog dialog = box.createDialog(app.qiscreen, "Scroll Speed");
			    dialog.setVisible(true);
			}


			@Override
			public GlobalState getActiveState() {
				return GlobalState.SETTINGS_MENU;
			}
		};
		
	}

}
