import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class RestaurantMenu {
	
	private JFrame loginFrame;

	private JFrame frame;
	private JPanel homePanel;
	private JPanel couponPanel;
	private JPanel menuPanel;
	private ArrayList<FoodItem> menuItems;
	
	private JPanel cartPanel;
	private JPanel innerCartPanel;
	private JScrollPane cartScrollPane;
	private JLabel subtotal;
	
	private int placeInLine = 0;
	
	private JPanel loginPanel;
	private JButton loginButton;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		// example for adding a customer, adding fooditems to menu, then adding
		// fooditems to a users order and printing the order, price updates accordingly
		Customer newCustomer = new Customer("yaneric", "password");
		FoodItem sandwich = new FoodItem("Sandwich",8.99);
		FoodItem sandwich2 = new FoodItem("Italian Sub",7.99);
		newCustomer.setPhone("5034736866");
		newCustomer.addItem(sandwich);
		newCustomer.addItem(sandwich2);
		newCustomer.orders[0].printOrder();
		System.out.println("Current total: " + newCustomer.orders[0].getTotalPrice());
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RestaurantMenu window = new RestaurantMenu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RestaurantMenu() {
		menuItems = new ArrayList<FoodItem>();
		
		//what we can do is before we use initialize() we present user with login
		//window that will take info and process it before we even open the menu
		
		//take that login info and pass it to initialize to start the menu
		Customer customer = new Customer("Eric Yan", "darkmagic10");
		initialize(customer);
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Customer currentUser) {
		
		
		/* ---------------------------------- Window Frame -------------------------------------- */
		// create a frame, this is the window of the application
		frame = new JFrame("SubZilla");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1920,1080);
        frame.setVisible(true);
        /* --------------------------------------------------------------------------------------- */
      
        
        /* ---------------------------------- Home Panel ----------------------------------------- */
        
        // create a Panel that sits inside the frame
        homePanel = new JPanel();
        homePanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        homePanel.setSize(1920,1080);
        frame.add(homePanel);
        
        //creating the title, set the constraints
        //constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        constraints.weighty = 2;
        
		JLabel title = new JLabel("SubZilla");
		title.setSize(290, 70);
		title.setFont(new Font("Impact", Font.PLAIN, 80));
		homePanel.add(title,constraints);
		
		/* ----------------------------------------------------------------------------------------- */
		
		constraints.gridx = 2;
		constraints.gridy = 0;
		constraints.ipadx = 100;
		constraints.ipady = 100;
		constraints.anchor = GridBagConstraints.FIRST_LINE_END;
		constraints.fill = GridBagConstraints.BOTH;
		//constraints.weightx = 1;
		constraints.weighty = 2;
		loginPanel = new JPanel();
		loginButton = new JButton("Login");
		loginPanel.add(loginButton);
		homePanel.add(loginPanel,constraints);
		
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		
		});
		
		
		
		/* ---------------------------------- Cart Panel -------------------------------------- */
		// create a frame, this is the window of the application
		constraints.gridx = 2;
		constraints.gridy = 1;
		constraints.ipadx = 25;
		constraints.ipady = 25;
		constraints.anchor = GridBagConstraints.LINE_END;
		constraints.fill = GridBagConstraints.BOTH;
		//constraints.weightx = 1;
		constraints.weighty = 1;
		

        innerCartPanel = new JPanel();
        innerCartPanel.setLayout(new BoxLayout(innerCartPanel, BoxLayout.Y_AXIS));
        innerCartPanel.setBorder(BorderFactory.createTitledBorder("Border"));
        
        cartPanel = new JPanel();
        cartPanel.setLayout(new BoxLayout(cartPanel, BoxLayout.Y_AXIS));
        cartPanel.setBorder(BorderFactory.createTitledBorder("Cart"));
        cartPanel.add(innerCartPanel);
        
        homePanel.add(cartPanel,constraints);
        /* --------------------------------------------------------------------------------------- */
		
		
		/* -------------------------------------- Place Order Button -------------------------------------- */
        constraints.gridx = 2;
        constraints.gridy = 3;
        constraints.ipadx = 70;
        constraints.ipady = 50;
        constraints.anchor = GridBagConstraints.PAGE_END;
        constraints.fill = GridBagConstraints.NONE;
        constraints.weighty = 1;
        
        JPanel buttonPanel = new JPanel();
		JButton placeOrder = new JButton("Place Order");
		placeOrder.setSize(100,50);
		placeOrder.setLocation(1700, 900);
		buttonPanel.add(placeOrder);
		homePanel.add(buttonPanel,constraints);
		
		placeOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				homePanel.setVisible(false);
				placeInLine++;
				
				JPanel checkoutPanel = new JPanel();
				checkoutPanel.setLayout(new GridBagLayout());
				GridBagConstraints checkoutConstraints = new GridBagConstraints();
				
				checkoutPanel.setSize(1920,1080);
		        
		        //creating the title, set the constraints
		        //constraints.fill = GridBagConstraints.HORIZONTAL;
				checkoutConstraints.gridx = 0;
				checkoutConstraints.gridy = 0;
		        checkoutConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
		        checkoutConstraints.weighty = 2;
		        
				JLabel OrderPlacedTitle = new JLabel("Order Placed");
				//OrderPlacedTitle.setSize(290, 70);
				OrderPlacedTitle.setFont(new Font("Impact", Font.PLAIN, 80));
				checkoutPanel.add(OrderPlacedTitle,checkoutConstraints);
				
				JLabel placeInLineLabel = new JLabel("Your place in line is: " + placeInLine);
				//placeInLineLabel.setSize(290, 70);
				placeInLineLabel.setFont(new Font("Impact", Font.PLAIN, 20));
				checkoutConstraints.gridy = 1;
				checkoutPanel.add(placeInLineLabel,checkoutConstraints);
				
				
				JPanel orderSummaryPanel = new JPanel();
				checkoutConstraints.ipadx = 200;
				checkoutConstraints.ipady = 300;
				orderSummaryPanel.setBorder(BorderFactory.createTitledBorder("Summary"));
				checkoutConstraints.gridy = 2; 
				checkoutConstraints.fill = GridBagConstraints.HORIZONTAL;
			
				checkoutPanel.add(orderSummaryPanel,checkoutConstraints);
				
				frame.add(checkoutPanel);
				
				
			}
		});
		
        constraints.gridx = 2;
        constraints.gridy = 2;
        constraints.ipadx = 50;
        constraints.ipady = 50;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.weighty = 0;
        subtotal = new JLabel("Total: $" + String.valueOf(currentUser.getOrdersArr()[currentUser.getNumOfOrders()].getTotalPrice()));
        homePanel.add(subtotal,constraints);
		/* ----------------------------------------------------------------------------------------- */
	

		
		/* -------------------------------------- Coupon Panel ------------------------------------- */
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.anchor = GridBagConstraints.LINE_START;
		constraints.ipadx = 50;
		//constraints.weightx = 1;
		constraints.weighty = 1;
		
		couponPanel = new JPanel();
		couponPanel.setSize(250, 600);
		couponPanel.setLocation(50,200);
		couponPanel.setBorder(BorderFactory.createTitledBorder("Coupons"));
		homePanel.add(couponPanel,constraints);
		
		JLabel couponTitle = new JLabel("Rewards Program");
		couponTitle.setSize(150,30);
		couponTitle.setLocation(125, 10);
		couponTitle.setHorizontalAlignment(JLabel.CENTER);
		couponPanel.add(couponTitle);
		
		JLabel couponInfo = new JLabel("BUY TEN SUBS, GET ONE FREE");
		couponInfo.setSize(250,30);
		couponInfo.setLocation(75, 125);
		couponInfo.setHorizontalAlignment(JLabel.CENTER);	//centers label inside of the label container
		couponPanel.add(couponInfo);
		/* ---------------------------------------------------------------------------------------- */
		
		
		/* --------------------------------------- Menu Panel ------------------------------------- */
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.gridheight = 1;
		//constraints.weightx = 1;
		constraints.ipadx = 600;
		constraints.ipady = 600;
		constraints.insets = new Insets(50,50,50,50);
		
		menuPanel = new JPanel();			//creates a panel with two columns and infinite rows
		menuPanel.setSize(1170,500);
		menuPanel.setLocation(350, 300);
		menuPanel.setLayout(new FlowLayout());
		menuPanel.setBorder(BorderFactory.createTitledBorder("MENU"));
		menuPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);		//adds objects in from left to right
		homePanel.add(menuPanel,constraints);

        // creating a user object with name Eric
		User user = new User();
		user.setName("Eric");
		
		//creating owner object
		Owner owner = new Owner();
		owner.setName("I am the owner");
		owner.initializeMenu();			//I get an error every time I add stuff to the menu and prices array
		/* --------------------------------------------------------------------------------------- */
		
		owner.createFoodItem(menuItems,"Beef and Cheese", 8.99);
		owner.createFoodItem(menuItems,"Buffalo Chicken", 7.99);
		
		// execute create menu
		renderMenu(currentUser);
		
        // button listener template
		/*
		
		userButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,user.getName());
			}
		});
		
		
		ownerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, owner.getName());
			}
		});
		
		menuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuFrame.setVisible(true);
			}
		});*/
	}
	
	
	// create menu should read the CSV file with food items and then add to menu
	private void renderMenu(Customer currentUser) {
		// while there are still lines to read food items
			// read the food item and add it to the array
		
		// for each food item in the array
			// create respective labels and add to the menu, dynamically adds menu items
		menuPanel.removeAll();
		menuItems.forEach((FoodItem) -> {
			JButton newItem = new JButton();
			newItem.setLayout(new FlowLayout());

			JLabel newLabel = new JLabel(FoodItem.getName());
			JLabel newPrice = new JLabel(String.valueOf(FoodItem.getPrice()));
			newItem.setSize(100,100);
			newItem.add(newLabel);
			newItem.add(newPrice);
			menuPanel.add(newItem);
			newItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// add the food item to the current user's cart
					currentUser.addItem(FoodItem);
					
					// this is just printing it in the console so we can debug
					currentUser.getOrdersArr()[currentUser.getNumOfOrders()].printOrder();
					
					// update the cart GUI
					renderCart(currentUser);
				}
			});
		});
	}
	
	private void renderCart(Customer currentUser) {
		cartPanel.removeAll();
		currentUser.getOrdersArr()[currentUser.getNumOfOrders()].getCart().forEach((FoodItem) -> {
			JButton cartItem = new JButton(FoodItem.getName() + " " + FoodItem.getPrice() + "      x");

			cartItem.setAlignmentX(Component.CENTER_ALIGNMENT);
			cartPanel.add(cartItem,BorderLayout.PAGE_END);
			cartPanel.revalidate();	
			
			subtotal.setText("Total: $" + String.valueOf(currentUser.getOrdersArr()[currentUser.getNumOfOrders()].getTotalPrice()));
			
			cartItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					currentUser.getOrdersArr()[currentUser.getNumOfOrders()].removeFromOrder(FoodItem);
					cartPanel.remove(cartItem);
					cartPanel.revalidate();
					cartPanel.repaint();
					subtotal.setText("Total: $" + String.valueOf(currentUser.getOrdersArr()[currentUser.getNumOfOrders()].getTotalPrice()));
				}
			});
			cartPanel.repaint();
		});
	}
	
	private void login() {		
		loginFrame = new JFrame("Login");
		loginFrame.setBounds(100, 100, 450, 300);
		//loginFrame.setDefaultCloseOperation();
		loginFrame.pack();
		loginFrame.setSize(500,600);
		loginFrame.setVisible(true);
		JPanel loginPanel = new JPanel();
		JLabel newLogin = new JLabel("LOGIN");
		JTextField username = new JTextField(25);
		JTextField password = new JTextField(25);
		JButton enter = new JButton("Enter");
		loginFrame.add(loginPanel);
		loginPanel.add(username);
		loginPanel.add(password);
		
		username.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input = username.getText();
				newLogin.setText(input);	
			}
		});
		password.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input = password.getText();
				newLogin.setText(input);	
			}
		});
		loginPanel.add(enter);
	}
	
}
