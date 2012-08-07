package biz.wittkemper.jfire.forms;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

public class FError extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7500258571439273086L;
	private JScrollPane scrollPane;
	private JTextPane errorMessage;
	private JTextPane errorStack;
	private JButton btnOk;
	private JLabel lblFehlermeldung;
	private JLabel lblStacktrace;
	private JLabel lblNewLabel;

	public FError(Exception exception) {
		initFrame();
		setExecption(exception);
	}

	private void setExecption(Exception exception) {
		StringBuilder text = new StringBuilder();

		for (StackTraceElement e : exception.getStackTrace()) {
			text.append(e.getFileName() + "-" + e.getClassName() + "-"
					+ e.getMethodName() + "-" + e.getLineNumber() + "\n");
		}
		lblStacktrace.setText(text.toString());
		lblFehlermeldung.setText(exception.getMessage());
	}

	/**
	 * Create the dialog.
	 */
	private void initFrame() {
		setBounds(100, 100, 452, 655);

		scrollPane = new JScrollPane();

		errorStack = new JTextPane();
		errorStack.setEditable(false);

		btnOk = new JButton("OK");

		lblFehlermeldung = new JLabel("Fehlermeldung");

		lblStacktrace = new JLabel("Stacktrace");

		lblNewLabel = new JLabel("JFIRE Fehlermeldung");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBackground(new Color(238, 232, 170));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGap(185)
										.addComponent(btnOk,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE).addGap(213))
						.addGroup(
								groupLayout.createSequentialGroup()
										.addContainerGap()
										.addComponent(lblFehlermeldung)
										.addContainerGap(334, Short.MAX_VALUE))
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																scrollPane,
																Alignment.TRAILING,
																GroupLayout.DEFAULT_SIZE,
																428,
																Short.MAX_VALUE)
														.addComponent(
																errorStack,
																Alignment.TRAILING,
																GroupLayout.DEFAULT_SIZE,
																428,
																Short.MAX_VALUE))
										.addContainerGap())
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(lblStacktrace,
												GroupLayout.PREFERRED_SIZE,
												106, GroupLayout.PREFERRED_SIZE)
										.addContainerGap(334, Short.MAX_VALUE))
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE,
								452, Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addGap(24)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE,
								15, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblFehlermeldung)
						.addGap(5)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE,
								220, GroupLayout.PREFERRED_SIZE)
						.addGap(52)
						.addComponent(lblStacktrace)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(errorStack, GroupLayout.PREFERRED_SIZE,
								222, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED,
								GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnOk).addContainerGap()));

		errorMessage = new JTextPane();
		errorMessage.setEditable(false);
		scrollPane.setViewportView(errorMessage);
		getContentPane().setLayout(groupLayout);

	}
}
