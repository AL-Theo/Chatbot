package chatbot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;


@SuppressWarnings("serial")
public class Chatbot extends JPanel {
	
	private static final int rows = 30; 	// rows in the chat view jtextArea
    private static final int cols = 32; 	// columns in the chat view jtextArea and text entry area
                                        
    
    private static final int textRows = 2; 	//rows in the text entry jtextArea
    private static final int hGap = 10;   	// horizontal gap for BorderLayout                                        
    private static final int vGap = 5; 		// vertical gap for our BorderLayout
    private static final int appGap = 5; 	// gap for empty border that goes around entire app
                                          	
    
    private static final String appTitle = "Hello! I'm Backpacker Bot"; 	//app title
    private static final float titleSize = 16f; 							// size of the title JLabel text
    
    
    private static final int bWidth = 70;	//button size and Location
    private static final int bLength = 35;
    private static final int bLocation_X =  330;
    private static final int bLocation_Y = 0;
                                                   
    
    private JTextArea chat = new JTextArea(rows, cols);
    private JTextArea text = new JTextArea(textRows, cols);
    private JButton button = new JButton("Random");
	
    
    public Chatbot() {
    	
    	// label to display the title in bold large text
        JLabel titleLabel = new JLabel(appTitle, SwingConstants.CENTER);
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.ITALIC, titleSize));
        

        // rules to chat display area
        // focusable so that chat area is not editable
        chat.setWrapStyleWord(true);
        chat.setLineWrap(true);
        chat.setFocusable(false);
        

        // add scroll to chat area
        JScrollPane chatScroll = new JScrollPane(chat);
        chatScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        

        // rules for JTextArea
        text.setWrapStyleWord(true);
        text.setLineWrap(true);
        
        
        // button layout
        button.setSize(bWidth, bLength);
        button.setLocation(bLocation_X, bLocation_Y);
        text.add(button);
       
        
        //Random country selection method using button
	    button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				int randCountry = (int) (Math.random()*4+1);
				
				if(randCountry == 1) {
					bot("England is a country in Europe, it has a population of 66.4 mil \n" +
							"A popular dish there is Fish and Chips, yum! \n" +
							"Their currency is the Pound Sterling, very powerful \n" +
							"And the capital is the great city of London! \n");	
				}
				
				else if(randCountry == 2) {
					bot("Brazil is a country in South America \n" +
							"The population is of 206 mil \n" +
							"A popular dish there is Feijoada, yuum! \n" +
							"Their currency is the Real \n" +
							"And the capital is Brazilia, built in the heart of the Amazon! \n" +
							"Oh, must mention as some don't know \n" +
							"Brazilians speak Portuguese, not Spanish \n");
				}
				
				else if(randCountry == 3) {
					bot("Japan is a country in Asia \n" +
							"The population is of 126.8 mil \n" +
							"A popular dish there is Sushi, yum! Yum! \n" +
							"Their currency is the Yen \n" +
							"The capital is Amazing city of Tokyo! \n" +
							"Some of the coolest places to go there are \n" +
							"Akihabara, Shinjuku and Harajuku \n");
				}
				
				else if(randCountry == 4) {
					bot("Thailand is a country in South East Asia \n" +
							"The population is of 69.4 mil \n" +
							"A popular dish there is the yummy Padthai \n" +
							"Their currency is the Thai Baht \n" +
							"The capital is Bangkok \n" +
							"Thailand is a great place to backpack with little money! \n" +
							"If you get Chance go Island hoping there! \n" +
							"Than explore Chiang Mai in the north! \n");
				}
				
				else if(randCountry == 5) {
					bot("Spain is a country in Europe \n" +
							"The population is of 46.72 mil \n" +
							"A popular dish there is Paella \n" +
							"Their currency is the Euro \n" +
							"The capital is Madrid, a beaautifuul! old city \n");
				}
			}
		});
        
        
        // set enter key so that we can write multilined text on textArea
        // also so we can press enter to submit text
        setEnterKeyBinding(text);
        

        // add an empty border around entire application
        setBorder(BorderFactory.createEmptyBorder(appGap, appGap, appGap, appGap));
        
        
        // make the main layout a BorderLayout
        setLayout(new BorderLayout(hGap, vGap));
        
        
        // add our components to the GUI
        add(titleLabel, BorderLayout.PAGE_START);
        add(chatScroll, BorderLayout.CENTER);
        add(text, BorderLayout.PAGE_END);
    }
    

    // allows us to enter and submit on written text in JTextArea
    private void setEnterKeyBinding(JTextArea textArea) {
    	
        int condition = JComponent.WHEN_FOCUSED; 			// only for focused entry key
        InputMap inputMap = textArea.getInputMap(condition);
        ActionMap actionMap = textArea.getActionMap();
        

        KeyStroke entryKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
        KeyStroke ctrlEntryKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, KeyEvent.CTRL_DOWN_MASK);
        

        // first give ctrl-enter the action held by enter
        Object entryKey = inputMap.get(entryKeyStroke);
        Action entryAction = actionMap.get(entryKey);
        inputMap.put(ctrlEntryKeyStroke, ctrlEntryKeyStroke.toString());
        actionMap.put(ctrlEntryKeyStroke.toString(), entryAction);
        

        // now give enter key a new Action
        EnterKeyAction enterKeyAction = new EnterKeyAction();
        inputMap.put(entryKeyStroke, entryKeyStroke.toString());
        actionMap.put(entryKeyStroke.toString(), enterKeyAction);
    }

  
    
    // Sample responses from chatbot
   private class EnterKeyAction extends AbstractAction {
    
        @Override
		public void actionPerformed(ActionEvent e) {
				
	    	String eText = text.getText();
			chat.append("Human: " + eText + "\n\n");
			text.setText("");	
				
			if(eText.contains("Hi")){
				bot("Hello there!");	
			}
				
			else if(eText.contains("How are you?")) {
				bot("I'm doing well");
			}
				
			else if(eText.contains("England")) {
				bot("England is a country in Europe, it has a population of 66.4 mil \n" +
						"A popular dish there is Fish and Chips, yum! \n" +
						"Their currency is the Pound Sterling, very powerful \n" +
						"And the capital is the great city of London!");	
					}
				
			else if(eText.contains("Brazil")) {
				bot("Brazil is a country in South America \n" +
						"The population is of 206 mil \n" +
						"A popular dish there is Feijoada, yuum! \n" +
						"Their currency is the Real \n" +
						"And the capital is Brazilia, built in the heart of the Amazon! \n" +
						"Oh, must mention as some don't know \n" +
						"Brazilians speak Portuguese, not Spanish");
					}
				
			else if(eText.contains("Japan")) {
				bot("Japan is a country in Asia \n" +
						"The population is of 126.8 mil \n" +
						"A popular dish there is Sushi, yum! Yum! \n" +
						"Their currency is the Yen \n" +
						"The capital is Amazing city of Tokyo! \n" +
						"Some of the coolest places to go there are \n" +
						"Akihabara, Shinjuku and Harajuku \n");
					}
				
			else if(eText.contains("Thailand")) {
				bot("Thailand is a country in South East Asia \n" +
						"The population is of 69.4 mil \n" +
						"A popular dish there is the yummy Padthai \n" +
						"Their currency is the Thai Baht \n" +
						"The capital is Bangkok \n" +
						"Thailand is a great place to backpack with little money! \n" +
						"If you get Chance go Island hoping there! \n" +
						"Than explore Chiang Mai in the north! \n");
					}
				
			else if(eText.contains("Spain")) {
				bot("Spain is a country in Europe \n" +
						"The population is of 46.72 mil \n" +
						"A popular dish there is Paella \n" +
						"Their currency is the Euro \n" +
						"The capital is Madrid, a beaautifuul! old city \n");
					}
				
			else if(eText.contains("Thank you")) {
				bot("Your Welcome! Hopefully that Helps!");
					}
			
			else {
				bot("Sorry, I didn't get that. Make sure the first letter is always capitalize and the country is one of the countries I know. \n");
			}
        }

	};
	
    
	//Method to label bot response
    public void bot(String str) {
		chat.append("Backpacker Bot: " + str + "\n");
	}
    
    
    //Method to create and call app
    private static void showApp() {
        
    	Chatbot mainPanel = new Chatbot();

        JFrame frame = new JFrame("Chatbot");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainPanel);

        // pack the JFrame so that it will size itself to its components
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    
    public static void main(String[] args) {
    	
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                showApp();
            }
        });
    }
}
