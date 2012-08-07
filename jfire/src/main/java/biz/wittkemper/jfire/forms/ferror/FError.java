package biz.wittkemper.jfire.forms.ferror;

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

import org.hibernate.JDBCException;

public class FError extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7500258571439273086L;
	private JScrollPane scrollPane;
	private JTextPane errorMessage;
	private JButton btnOk;
	private JLabel lblFehlermeldung;
	private JLabel lblStacktrace;
	private JLabel lblNewLabel;
	private JScrollPane scrollPane_1;
	private JTextPane errorStack;

	public FError() {
		initFrame();
	}

	public FError(Exception exception) {
		initFrame();
		setExecption(exception);
	}

	public FError(JDBCException exception) {
		initFrame();
		StringBuilder text = new StringBuilder();

		for (StackTraceElement e : exception.getStackTrace()) {
			text.append(e.getFileName() + "-" + e.getClassName() + "-"
					+ e.getMethodName() + "-" + e.getLineNumber() + "\n");
		}
		errorStack.setText(text.toString());
		errorMessage.setText(exception.getMessage() + "\n"
				+ exception.getSQLException().getMessage());
	}

	private void setExecption(Exception exception) {
		StringBuilder text = new StringBuilder();

		for (StackTraceElement e : exception.getStackTrace()) {
			text.append(e.getFileName() + "-" + e.getClassName() + "-"
					+ e.getMethodName() + "-" + e.getLineNumber() + "\n");
		}
		errorStack.setText(text.toString());
		errorMessage.setText(exception.getMessage());
	}

	/**
	 * Create the dialog.
	 */
	private void initFrame() {
		setBounds(100, 100, 671, 660);

		scrollPane = new JScrollPane();

		btnOk = new JButton("OK");

		lblFehlermeldung = new JLabel("Fehlermeldung");
		lblFehlermeldung.setBackground(new Color(238, 232, 170));

		lblStacktrace = new JLabel("Stacktrace");

		lblNewLabel = new JLabel("JFIRE Fehlermeldung");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBackground(new Color(238, 232, 170));

		scrollPane_1 = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						groupLayout.createSequentialGroup().addContainerGap()
								.addComponent(lblFehlermeldung)
								.addContainerGap(334, Short.MAX_VALUE))
				.addGroup(
						groupLayout
								.createSequentialGroup()
								.addContainerGap()
								.addComponent(scrollPane,
										GroupLayout.DEFAULT_SIZE, 428,
										Short.MAX_VALUE).addContainerGap())
				.addGroup(
						groupLayout
								.createSequentialGroup()
								.addContainerGap()
								.addComponent(lblStacktrace,
										GroupLayout.PREFERRED_SIZE, 106,
										GroupLayout.PREFERRED_SIZE)
								.addContainerGap(334, Short.MAX_VALUE))
				.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 452,
						Short.MAX_VALUE)
				.addGroup(
						groupLayout
								.createSequentialGroup()
								.addContainerGap()
								.addComponent(scrollPane_1,
										GroupLayout.DEFAULT_SIZE, 428,
										Short.MAX_VALUE).addContainerGap())
				.addGroup(
						groupLayout
								.createSequentialGroup()
								.addGap(189)
								.addComponent(btnOk, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE).addGap(209)));
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
						.addGap(39)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE,
								209, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(btnOk).addGap(18)));

		errorStack = new JTextPane();
		errorStack.setEditable(false);
		scrollPane_1.setViewportView(errorStack);

		errorMessage = new JTextPane();
		errorMessage.setEditable(false);
		scrollPane.setViewportView(errorMessage);
		getContentPane().setLayout(groupLayout);

	}
}
