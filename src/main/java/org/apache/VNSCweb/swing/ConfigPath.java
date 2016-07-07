package org.apache.VNSCweb.swing;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * This program demonstrates using java.util.Properties class to read and write
 * settings for Java application.
 *
 * @author www.codejava.net
 *
 */
public class ConfigPath extends JFrame {

    private File configFile = new File("src/main/resources/config.properties");
    private Properties configProps;

    private JLabel labelHost = new JLabel("Path name: ");

    private JTextField textHost = new JTextField(20);

    private JButton buttonSave = new JButton("Save");

    public ConfigPath() {
        super("Properties Configuration Demo");
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 5, 10);
        constraints.anchor = GridBagConstraints.WEST;

        add(labelHost, constraints);

        constraints.gridx = 1;
        add(textHost, constraints);

        constraints.gridy = 2;
        constraints.gridx = 0;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        add(buttonSave, constraints);

        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    saveProperties();
                    JOptionPane.showMessageDialog(ConfigPath.this,
                            "Properties were saved successfully!");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(ConfigPath.this,
                            "Error saving properties file: " + ex.getMessage());
                }
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        try {
            loadProperties();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "The config.properties file does not exist, default properties loaded.");
        }
        textHost.setText(configProps.getProperty("Path"));
    }

    private void loadProperties() throws IOException {
        Properties defaultProps = new Properties();
        // sets default properties
        defaultProps.setProperty("Path", "/home/haonguyen/data");

        configProps = new Properties(defaultProps);

        // loads properties from file
        InputStream inputStream = new FileInputStream(configFile);
        configProps.load(inputStream);
        inputStream.close();
    }

    private void saveProperties() throws IOException {
        configProps.setProperty("Path", textHost.getText());
        OutputStream outputStream = new FileOutputStream(configFile);
        configProps.store(outputStream, "Path setttings");
        outputStream.close();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ConfigPath();
            }
        });
    }
}
