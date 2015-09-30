/*
 * Window.java
 * 
 * Version: 3
 * 
 * Revisions:
 *      Programmed the game's UI.
 *      Connected the controls with the Controller class.
 *      Beautified the JLists with colors and sorted items.
 *      Added Life Force and Experience text on the JProgressBar.
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * The UI for the game.
 * 
 * @author Ben
 */
public class Window extends JFrame implements Observer {
    private DefaultListModel<Hall>hallsModel = new DefaultListModel<Hall>();
    private DefaultListModel<String> 
    playerItemsModel = new DefaultListModel<String>(),
    roomItemsModel = new DefaultListModel<String>();

    public JList<Hall> lstHalls = new JList<Hall>(hallsModel);
    public JList<String> lstPlayerItems = new JList<String>(playerItemsModel);
    public JList<String> lstRoomItems = new JList<String>(roomItemsModel);

    public JButton btnHall = new JButton("Enter Hallway");
    public JButton btnDrop = new JButton("Drop Item");
    public JButton btnPickUp = new JButton("Pick Up Item");

    private JTextArea output = new JTextArea();

    private JProgressBar playerHealth = new JProgressBar(0, 100);

    /**
     * Constructs a UI for the game
     * 
     * @param gameValues The game values the player should know about.
     */
    public Window(GameValues gameValues) {
        gameValues.addObserver(this);

        btnHall.setEnabled(false);
        btnDrop.setEnabled(false);
        btnPickUp.setEnabled(false);

        playerHealth.setValue(100);
        playerHealth.setString("Life Force: 100");
        playerHealth.setStringPainted(true);

        JPanel centerPanel = new JPanel(new BorderLayout());
        output.setEditable(false);
        output.setBackground(Color.WHITE);
        playerHealth.setPreferredSize(new Dimension(0, 27));
        centerPanel.add(new JLabel("Game Output", 0), BorderLayout.NORTH);
        centerPanel.add(new JScrollPane(output), BorderLayout.CENTER);
        centerPanel.add(playerHealth, BorderLayout.SOUTH);

        lstPlayerItems.setCellRenderer(new ItemListCellRenderer());
        JPanel nWestPanel = new JPanel(new BorderLayout());
        nWestPanel.add(new JLabel("Your Items", 0), BorderLayout.NORTH);
        nWestPanel.add(new JScrollPane(lstPlayerItems), BorderLayout.CENTER);
        nWestPanel.add(btnDrop, BorderLayout.SOUTH);

        lstRoomItems.setCellRenderer(new ItemListCellRenderer());
        JPanel cWestPanel = new JPanel(new BorderLayout());
        cWestPanel.add(new JLabel("Items in Room", 0), BorderLayout.NORTH);
        cWestPanel.add(new JScrollPane(lstRoomItems), BorderLayout.CENTER);
        cWestPanel.add(btnPickUp, BorderLayout.SOUTH);

        JPanel sWestPanel = new JPanel(new BorderLayout());
        sWestPanel.setPreferredSize(new Dimension(125, 0));
        sWestPanel.add(new JLabel("Hallways", 0), BorderLayout.NORTH);
        sWestPanel.add(new JScrollPane(lstHalls), BorderLayout.CENTER);
        sWestPanel.add(btnHall, BorderLayout.SOUTH);

        JPanel westPanel = new JPanel(new GridLayout(3, 0, 0, 10));
        westPanel.setPreferredSize(new Dimension(125, 0));
        westPanel.add(nWestPanel);
        westPanel.add(cWestPanel);
        westPanel.add(sWestPanel);

        setLayout(new BorderLayout(2, 0));
        add(westPanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);

        gameValues.output("Game started");
        update(gameValues, null);

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    /**
     * Update the values from the game
     */
    public void update(Observable gameValues, Object obj) {
        GameValues values = (GameValues)gameValues;
        String newOutput = values.getNewOutput();
        output.append(newOutput);
        output.setCaretPosition(output.getText().length());

        hallsModel.clear();
        playerItemsModel.clear();
        roomItemsModel.clear();

        for (Hall h : values.getHalls()) hallsModel.addElement(h);

        List<String> plItems = new ArrayList<String>(values.getPlayerItems());
        Collections.sort(plItems, ItemComparator.instance);
        for (String s : plItems) playerItemsModel.addElement(s);

        List<String> rmItems = new ArrayList<String>(values.getRoomItems());
        Collections.sort(rmItems, ItemComparator.instance);
        for (String s : rmItems) roomItemsModel.addElement(s);

        btnHall.setEnabled(lstHalls.getSelectedValue() != null);
        btnDrop.setEnabled(lstPlayerItems.getSelectedValue() != null);
        btnPickUp.setEnabled(lstRoomItems.getSelectedValue() != null);
        
        playerHealth.setValue(values.getPlayerHealth());
        playerHealth.setString(
                "Life Force: " + values.getPlayerHealth() +
                "          |          Experience: " + values.getPlayerExp());

        if (values.isGameOver()) {
            btnHall.setEnabled(false);
            btnDrop.setEnabled(false);
            btnPickUp.setEnabled(false);
            lstHalls.setEnabled(false);
            lstPlayerItems.setEnabled(false);
            lstRoomItems.setEnabled(false);
        }
    }
}

/**
 * Makes JLists with items pretty by adding color
 * 
 * @author Ben Woodworth
 */
class ItemListCellRenderer extends DefaultListCellRenderer {
    @SuppressWarnings("rawtypes")
    public Component getListCellRendererComponent(JList list,  
            Object value, int index, boolean isSelected,  
            boolean cellHasFocus) {
        super.getListCellRendererComponent(
                list, value, index, isSelected, cellHasFocus);

        String item = value.toString();
        if (Items.isAmulet(value.toString())) {
            setForeground(new Color(212, 175, 55)); //Gold
        } else if (Items.isSword(item)) {
            setForeground(new Color(191, 63, 63)); //Red
        } else {
            setForeground(new Color(0, 191, 63)); //Green
        }
        return this;
    }
}

/**
 * Allows for item sorting in JLists
 * 
 * @author Ben Woodworth
 */
class ItemComparator implements Comparator<String> {
    public static final ItemComparator instance = new ItemComparator(); 
    public int compare(String a, String b) {
        if (Items.isAmulet(a)) return -1;
        if (Items.isAmulet(b)) return 1;
        if (Items.isSword(a)) return -1;
        if (Items.isSword(b)) return 1;
        return a.compareToIgnoreCase(b);
    }
}
