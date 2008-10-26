package com.googlecode.mibible.browser;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class BrowserFrame extends JFrame
{
    /** デフォルトのInsets */
    public static final Insets DEFAULT_INSETS = new Insets(2, 5, 2, 5);

	/** Mediator */
	private Mediator mediator = new Mediator();

    public void initialize()
    {
    	// Frameの初期表示領域の設定
        Rectangle bounds = new Rectangle();
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        bounds.width = (int) (size.width * 0.8);
        bounds.height = (int) (size.height * 0.8);
        bounds.x = (size.width - bounds.width) / 2;
        bounds.y = (size.height - bounds.height) / 2;
        setBounds(bounds);
    	
        // ウィンドウタイトルの設定
        setTitle("mibible browser");
        // ウィンドウのXボタンで閉じる
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // メニューを設定する
        setJMenuBar(getMenu());
        // Frameにレイアウトを設定する
        getContentPane().setLayout(new GridBagLayout());
        
        // Frame Layout
        // +----------------------------------+
        // | Tree Panel | Description Panel   |
        // +            |---------------------+
        // |            | Communication Panel |
        // +----------------------------------+
        
        // レイアウト
        GridBagConstraints  gbc;
        
        // Frame内部を左右に分けるPaneのレイアウトを設定する
        JSplitPane leftRightSplitPane = new JSplitPane();
        leftRightSplitPane.setDividerLocation((int) (bounds.width * 0.40));
        gbc = new GridBagConstraints();
        gbc.weightx = 1.0d;
        gbc.weighty = 1.0d;
        gbc.fill = GridBagConstraints.BOTH;
        getContentPane().add(leftRightSplitPane, gbc);

        // 右側のPane内部をを上下に分けるPaneのレイアウトを設定する
        JSplitPane topBottomSplitPane = new JSplitPane();
        topBottomSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        topBottomSplitPane.setDividerLocation((int) (bounds.height * 0.40));
        topBottomSplitPane.setOneTouchExpandable(true);
        
        // 右側のPane内部をを上下に分けるPaneの中身を設定する
        topBottomSplitPane.setLeftComponent(getDescriptionPanel(mediator));
        topBottomSplitPane.setRightComponent(getSnmpPanel(mediator));
        
        // Frame内部を左右に分けるPaneの中身を設定する
        leftRightSplitPane.setLeftComponent(getTreePanel(mediator));
        leftRightSplitPane.setRightComponent(topBottomSplitPane);
        
        // ステータスラベルを設定する
        JLabel statusLabel = new JLabel();
        gbc = new GridBagConstraints();
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(2, 5, 2, 5);
        getContentPane().add(statusLabel, gbc);
        
        // Mediatorにコンポーネントを設定する
        this.mediator.setStatusLabel(statusLabel);
    }
    
    private JMenuBar getMenu()
    {
    	JMenuBar menuBar = new JMenuBar();
    	
        // Create file menu
    	JMenu file = new JMenu("File");
        
    	// Create Open MIB item
    	JMenuItem open = new JMenuItem("Open MIB...");
    	open.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	// TODO
//                loadMib();
            }
        });
        file.add(open);
        
    	// Create Close MIB item
        JMenuItem close = new JMenuItem("Close MIB");
        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	// TODO
//                unloadMib();
            }
        });
        file.add(close);
        
        // Create Separator
        file.addSeparator();
        
    	// Create Exit item
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        file.add(exit);
        
        // Add file to menu bar
        menuBar.add(file);
    	
    	return menuBar;
    }
    
    private JPanel getTreePanel(Mediator mediator)
    {
    	TreePanel panel = new TreePanel(mediator);
    	panel.initialize();
    	return panel;
    }
    private JPanel getDescriptionPanel(Mediator mediator)
    {
    	DescriptionPanel panel = new DescriptionPanel(mediator);
    	panel.initialize();
    	return panel;
    }
    private JPanel getSnmpPanel(Mediator mediator)
    {
    	CommunicationPanel panel = new CommunicationPanel(mediator);
    	panel.initialize();
    	return panel;
    }
    
}