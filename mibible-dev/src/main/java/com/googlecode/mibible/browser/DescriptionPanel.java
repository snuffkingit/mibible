package com.googlecode.mibible.browser;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class DescriptionPanel extends JPanel
{
    /** Mediator */
    private Mediator mediator;

    /** Description Area */
    private JTextArea descriptionArea = new JTextArea();
    
    public DescriptionPanel(Mediator mediator)
    {
        this.mediator = mediator;
    }

    public void initialize()
    {
        // レイアウトを設定する
        this.setLayout(new GridBagLayout());
        
        // Add descriptionArea
        descriptionArea.setEditable(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc = new GridBagConstraints();
        gbc.weightx = 1.0d;
        gbc.weighty = 1.0d;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(new JScrollPane(descriptionArea), gbc);
        
        // Mediatorにコンポーネントを設定する
        this.mediator.setDescriptionArea(descriptionArea);
    }
}
