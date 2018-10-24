import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.Arrays;


public class CalcGUI extends JFrame {
    private JTextField textField;
    private JButton a7Button;
    private JButton a8Button;
    private JButton a9Button;
    private JButton a4Button;
    private JButton a5Button;
    private JButton a6Button;
    private JButton a1Button;
    private JButton a2Button;
    private JButton a3Button;
    private JButton plusButton;
    private JButton minusButton;
    private JButton multButton;
    private JButton a0Button;
    private JButton sepButton;
    private JButton clearButton;
    private JButton divButton;
    private JButton resButton;
    private JPanel mainPanel;
    private JButton modButton;

    private CalcLogic engine;


    public CalcGUI() {

        this.engine = new CalcLogic();
        this.textField.setEditable(false);
        this.textField.setFocusable(true);
        this.textField.grabFocus();

        a7Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    setValue(BigDecimal.valueOf(7.0));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        a8Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    setValue(BigDecimal.valueOf(8.0));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        a9Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    setValue(BigDecimal.valueOf(9.0));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        a4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    setValue(BigDecimal.valueOf(4.0));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        a5Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    setValue(BigDecimal.valueOf(5.0));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        a6Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    setValue(BigDecimal.valueOf(6.0));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        a1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    setValue(BigDecimal.valueOf(1.0));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        a2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    setValue(BigDecimal.valueOf(2.0));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        a3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    setValue(BigDecimal.valueOf(3.0));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        a0Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    setValue(BigDecimal.valueOf(0.0));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        plusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                engine.isSeparatorSet = false;
                try {
                    setValue('+');
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        minusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                engine.isSeparatorSet = false;
                try {
                    setValue('-');
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        multButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                engine.isSeparatorSet = false;
                try {
                    setValue('*');
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        divButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                engine.isSeparatorSet = false;
                try {
                    setValue('/');
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        modButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                engine.isSeparatorSet = false;
                try {
                    setValue('%');
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                engine = new CalcLogic();
                textField.setText("");
            }
        });
        sepButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (engine.firstVal == null) {
                    engine.firstVal = new BigDecimal("0.0");
                }
                engine.isSeparatorSet = true;
            }
        });
        resButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    textField.setText(engine.getResult().stripTrailingZeros().toPlainString());
                }
                catch (Exception e) {
                    textField.setText(e.getMessage());
                    engine = new CalcLogic();
//                    JOptionPane.showMessageDialog(CalcGUI.super.rootPane, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                switch (e.getKeyCode()) {
                    case 107:
                        engine.isSeparatorSet = false;
                        try {
                            setValue('+');
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        break;
                    case 109:
                        engine.isSeparatorSet = false;
                        try {
                            setValue('-');
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        break;
                    case 106:
                        engine.isSeparatorSet = false;
                        try {
                            setValue('*');
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        break;
                    case 111:
                        engine.isSeparatorSet = false;
                        try {
                            setValue('/');
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        break;
                    case 110:
                        if (engine.firstVal == null) {
                            engine.firstVal = new BigDecimal("0.0");
                        }
                        engine.isSeparatorSet = true;
                        break;
                    case 44:
                        if (engine.firstVal == null) {
                            engine.firstVal = new BigDecimal("0.0");
                        }
                        engine.isSeparatorSet = true;
                        break;
                    case 8:
                        engine = new CalcLogic();
                        textField.setText("");
                        break;
                    case 127:
                        try {
                            setValue('/');
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        engine = new CalcLogic();
                        textField.setText("");
                        break;
                    case KeyEvent.VK_ENTER:
                        try {
                            textField.setText(engine.getResult().stripTrailingZeros().toPlainString());
                        }
                        catch (Exception eKey) {
                            textField.setText(eKey.getMessage());
                            engine = new CalcLogic();
//                            JOptionPane.showMessageDialog(CalcGUI.super.rootPane, eKey.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    default:
                        if (Character.isDigit(e.getKeyChar())) {
                            try {
                                setValue(new BigDecimal(String.valueOf(e.getKeyChar())+".0"));
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
                        }

                }
            }
        });
    }

    private <T> void setValue(T value) throws Exception {
        this.textField.setText("");
        if (value.getClass().getName().equals("java.lang.Character")) this.engine.setValue((Character) value);
        if (value.getClass().getName().equals("java.math.BigDecimal")) this.engine.setValue((BigDecimal) value);
        this.textField.setText(this.engine.getStringReprOfOperation());

    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator");
        frame.setResizable(false);
        frame.setContentPane(new CalcGUI().mainPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
