import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

public class erlang extends JFrame
{
	//各种按钮
	private JButton ok,clear,paint;
	private JRadioButton count_b,count_a,count_s;
	private ButtonGroup choose;
	private JTextField text_b,text_a,text_s;
	private JComboBox lbk;
	private JLabel label;
	private JPanel pok,pclear,pb,pa,ps,ptb,pta,pts,plbk,plabel,ppaint;
	private String number[]={"1","2","3","4","5"};
	
	//构造方法 
	public erlang()
	{
		//主框架
		super("erlang计数器");
		setBounds(400,150,390,150);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//创建ButtonGroup和按钮和text和ComboBox
		ok=new JButton("确认");
		clear=new JButton("清除");
		paint=new JButton("绘图");
		choose=new ButtonGroup();
		count_b=new JRadioButton("计算B(a,s)");
		count_a=new JRadioButton("计算a");
		count_s=new JRadioButton("计算s");
		count_b.setFont(new Font("Serif",Font.PLAIN,14));
		count_a.setFont(new Font("Serif",Font.PLAIN,14));
		count_s.setFont(new Font("Serif",Font.PLAIN,14));
		choose.add(count_b);
		choose.add(count_a);
		choose.add(count_s);
		count_b.setSelected(true);
		text_b=new JTextField("",15);
		text_b.setFont(new Font("Serif",Font.PLAIN,10));
		text_b.setEditable(false);
		text_a=new JTextField("",15);
		text_a.setFont(new Font("Serif",Font.PLAIN,10));
		text_s=new JTextField("",15);
		text_s.setFont(new Font("Serif",Font.PLAIN,10));
		label=new JLabel("小数点需精度");
		lbk=new JComboBox(number);
		lbk.setMaximumRowCount(3);
		lbk.setSelectedIndex(2);
		lbk.setFont(new Font("Serif",Font.PLAIN,14));
		//建立系统布局
		Container c=getContentPane();
		c.setLayout(null);//容器
		ppaint=new JPanel();
		ppaint.setLayout(new BorderLayout());
		c.add(ppaint);
		ppaint.add(paint);
		ppaint.setBounds(180, 85, 60, 25);//按钮paint
		pok=new JPanel();
		pok.setLayout(new BorderLayout());
		c.add(pok);
		pok.add(ok);
		pok.setBounds(310, 85, 60, 25);//按钮ok
		pclear=new JPanel();
		pclear.setLayout(new BorderLayout());
		c.add(pclear);
		pclear.add(clear);
		pclear.setBounds(245, 85, 60, 25);//按钮clear
		plabel=new JPanel();
		plabel.setLayout(new BorderLayout());
		c.add(plabel);
		plabel.add(label);
		plabel.setBounds(5, 90, 78, 20);//标签框
		plbk=new JPanel();
		plbk.setLayout(new BorderLayout());
		c.add(plbk);
		plbk.add(lbk);
		plbk.setBounds(85, 90, 35, 20);//精度框
		pb=new JPanel();
		pb.setLayout(new BorderLayout());
		c.add(pb);
		pb.add(count_b);
		pb.setBounds(30, 5, 90, 30);//选项B
		pa=new JPanel();
		pa.setLayout(new BorderLayout());
		c.add(pa);
		pa.add(count_a);
		pa.setBounds(140, 5, 90, 30);//选项a	
		ps=new JPanel();
		ps.setLayout(new BorderLayout());
		c.add(ps);
		ps.add(count_s);
		ps.setBounds(260, 5, 90, 30);//选项s
		ptb=new JPanel();
		ptb.setLayout(new BorderLayout());
		c.add(ptb);
		ptb.add(text_b);
		ptb.setBounds(30, 40, 80, 25);//文本框B
		pta=new JPanel();
		pta.setLayout(new BorderLayout());
		c.add(pta);
		pta.add(text_a);
		pta.setBounds(140, 40, 80, 25);//文本框a
		pts=new JPanel();
		pts.setLayout(new BorderLayout());
		c.add(pts);
		pts.add(text_s);
		pts.setBounds(260, 40, 80, 25);//文本框B
		setVisible(true);
		//设置监听器
		Itemhander hh=new Itemhander();
		count_b.addItemListener(hh);
		count_a.addItemListener(hh);
		count_s.addItemListener(hh);
		Actionhander aa=new Actionhander();
		ok.addActionListener(aa);
		clear.addActionListener(aa);
		paint.addActionListener(aa);
		
	}
	
	public static void main(String[] arguments)
	{
		//主函数
		erlang Erlang=new erlang();
	}
	
	public class Itemhander implements ItemListener
	{
		//选择计算项
		public void itemStateChanged(ItemEvent e) 
		{
			if(count_b.isSelected())
			{
			    text_b.setEditable(false);
			    text_a.setEditable(true);
			    text_s.setEditable(true);
				lbk.setVisible(true);
				label.setText("小数点需精度");
			}
			else if(count_a.isSelected())
			{
				text_b.setEditable(true);
				text_a.setEditable(false);
				text_s.setEditable(true);
				lbk.setVisible(true);
				label.setText("小数点需精度");
			}
			else if(count_s.isSelected())
			{
				text_b.setEditable(true);
				text_a.setEditable(true);
				text_s.setEditable(false);
				lbk.setVisible(false);
				label.setText("结果为整数");
			}
		}
	}
	
	public class Actionhander implements ActionListener
	{
		public void actionPerformed(ActionEvent a)
		{
			if(a.getSource()==clear)
			{   //清除所有内容
				text_b.setText("");
				text_a.setText("");
				text_s.setText("");
			}
			else if(a.getSource()==ok)
			{
				calculate();
			}
			else if(a.getSource()==paint)
			{
				paint();
			}
			
		}
		
		
		/******************中间函数****************/
		//计算B（s，a）的值
		public double B(int s,double a)
		{
			double count = 1;
			//如果s为0
			if(s==0)
			return 1;
			//如果s不为0	
			for(int i=1;i<=s;i++)
			count = (a*count)/(i+a*count);
			return count;
		}
		
		//四舍五入
		public double Adjust(double num)
		{
			double intermediate;
			intermediate=num;
			
			for(;num>=1;)
			num=num-1;
			
			if(num>=0.5)
			intermediate=intermediate+1;
			
			return intermediate;
		}
		/***************************************/
		
		//计算函数
		private void calculate()

		{
			//已知a,s计算b
			if(count_b.isSelected())
			{
				try
				{
					//正常输入
					int precise=Integer.parseInt((String)lbk.getSelectedItem())+1;//读取精度
					String sa = text_a.getText();
					String ss = text_s.getText();
					double a = Double.parseDouble(sa);
					int s = Integer.parseInt(ss);
					//限制a和s不能同时为0
					if(!(s==0&&a==0))
					{
						Double b = B(s,a);
						text_b.setText(""+((int)(Adjust(b*Math.pow(10,precise-1)))/Math.pow(10,precise-1)));
					}
					else
					{
						JOptionPane.showMessageDialog(erlang.this, "a,s不允许同时为0!");
						text_a.setText("");
						text_s.setText("");
					}
				}
				catch(Exception e)
				{
					//非法输入
					JOptionPane.showMessageDialog(erlang.this, "输入非法!请重新输入!");
					text_a.setText("");
					text_s.setText("");
				}

			}
			//已知b，s计算a
			else if(count_a.isSelected())
			{
				try
				{
					//正常输入
					int precise=Integer.parseInt((String)lbk.getSelectedItem())+1;//读取精度
					String sb = text_b.getText();
					String ss = text_s.getText();
					double b = Double.parseDouble(sb);
					int s = Integer.parseInt(ss);
					double a1=0,a2=1;
					double a=0;
					double point=1;
					int i;
					//判断b和s是否符合规范
					if(b<1&&b>0&&s!=0)
					{
						for(i=0;i<=precise;i++)
						{
							while(!(B(s,a1)<=b&&B(s,a2)>=b))
							{
								a1=a1+point;a2=a2+point;	
							}
							a=a1;
							point=point/10;
							a1=a1;a2=a1+point;
						}
						text_a.setText(""+(int)(Adjust(a*Math.pow(10,precise-1)))/Math.pow(10,precise-1));
						
					}
					else
					{
						if(!(b<1&&b>0))
						{
							JOptionPane.showMessageDialog(erlang.this, "B必须为0~1(不包含0和1)之间的数!");
							text_b.setText("");
						}
						if(s==0)
						{
							JOptionPane.showMessageDialog(erlang.this, "s不允许为0!");
							text_s.setText("");
						}

					}
				}
				catch(Exception e)
				{
					//非法输入
					JOptionPane.showMessageDialog(erlang.this, "输入非法!请重新输入!");
					text_b.setText("");
					text_s.setText("");
				}
				
			}	
			//已知a，b计算s
			else if(count_s.isSelected())
			{
				try
				{
					//正常输入
					String sa = text_a.getText(); 
					String sb = text_b.getText();
					double a = Double.parseDouble(sa);
					double b = Double.parseDouble(sb);
					//判断b是否符合规范
					if(b<1&&b>0)
					{
						int s = 1;
						while(B(s,a)>b) s++;
						text_s.setText(""+s);
					}
					else
					{
						JOptionPane.showMessageDialog(erlang.this, "B必须为0~1(不包含0和1)之间的数!");
						text_b.setText("");
					}
				}
				catch(Exception e)
				{
					//非法输入
					JOptionPane.showMessageDialog(erlang.this, "输入非法!请重新输入!");
					text_a.setText("");
					text_b.setText("");
				}

		    }	
	    }

		//绘图函数
		private void paint()
		{
			PaintFunction pp=new PaintFunction();
		}
	}

	//绘图类重建
	public class PaintFunction extends JFrame
	{
		//设置按钮
		private JButton confirm;
		private JRadioButton choose_b,choose_a,choose_s;
		private ButtonGroup chos;
		private JTextField tt_b,tt_a,tt_s;
		private JPanel pconfirm,pcb,pca,pcs,area,pttb,ptta,ptts;
		Container cc;
		
		public PaintFunction()
		{
			//设置主框
			//add(new PaintFunctionPanel());
			setSize(400,400);
			setTitle("绘制函数曲线");
			setLocationRelativeTo(null);//center
			setVisible(true);
			//初始化各种组建
			confirm=new JButton("确认");
			chos=new ButtonGroup();
			choose_b=new JRadioButton("给定B");
			choose_a=new JRadioButton("给定a");
			choose_s=new JRadioButton("给定s");
			choose_b.setFont(new Font("Serif",Font.PLAIN,14));
			choose_a.setFont(new Font("Serif",Font.PLAIN,14));
		    choose_s.setFont(new Font("Serif",Font.PLAIN,14));
			chos.add(choose_b);
			chos.add(choose_a);
			chos.add(choose_s);
			choose_s.setSelected(true);
			tt_b=new JTextField("",15);
			tt_b.setFont(new Font("Serif",Font.PLAIN,10));
			tt_b.setEditable(false);
			tt_a=new JTextField("",15);
			tt_a.setFont(new Font("Serif",Font.PLAIN,10));
			tt_a.setEditable(false);
			tt_s=new JTextField("",15);
			tt_s.setFont(new Font("Serif",Font.PLAIN,10));
			//建立布局
			cc=getContentPane();
			cc.setLayout(null);//容器
			pcb=new JPanel();
			pcb.setLayout(new BorderLayout());
			cc.add(pcb);
			pcb.add(choose_b);
			pcb.setBounds(20, 5, 90, 30);//选项B
			pca=new JPanel();
			pca.setLayout(new BorderLayout());
			cc.add(pca);
			pca.add(choose_a);
			pca.setBounds(120, 5, 90, 30);//选项a	
			pcs=new JPanel();
			pcs.setLayout(new BorderLayout());
			cc.add(pcs);
			pcs.add(choose_s);
			pcs.setBounds(220, 5, 90, 30);//选项s
			pttb=new JPanel();
			pttb.setLayout(new BorderLayout());
			cc.add(pttb);
			pttb.add(tt_b);
			pttb.setBounds(20, 40, 80, 25);//文本框B
			ptta=new JPanel();
			ptta.setLayout(new BorderLayout());
			cc.add(ptta);
			ptta.add(tt_a);
			ptta.setBounds(120, 40, 80, 25);//文本框a
			ptts=new JPanel();
			ptts.setLayout(new BorderLayout());
			cc.add(ptts);
			ptts.add(tt_s);
			ptts.setBounds(220, 40, 80, 25);//文本框B
			pconfirm=new JPanel();
			pconfirm.setLayout(new BorderLayout());
			cc.add(pconfirm);
			pconfirm.add(confirm);
			pconfirm.setBounds(310, 15, 60, 50);//按钮
			area=new JPanel();
			setVisible(true);
			//注册监听器
			Itemhhander hhh=new Itemhhander();
			choose_a.addItemListener(hhh);
			choose_b.addItemListener(hhh);
			choose_s.addItemListener(hhh);
			Actionhhander aaa=new Actionhhander();
			confirm.addActionListener(aaa);
			//说明
			JOptionPane.showMessageDialog(PaintFunction.this, "敬爱的老师（助教），由于我们采用的代码的效率问题，\n经实际测试" +
				         "给定B作图的功能容易导致机器计算死机，因此我们只画了两个点的图像，\n该部分依旧在我们的改进中，关于给定B的图像可以参见我" +
				         "们使用matlab做出的图像。\n给定a和给定s的作图功能良好可用。感谢您的使用。");
		}
		
		public class Itemhhander implements ItemListener
		{
			//选择计算项
			public void itemStateChanged(ItemEvent e) 
			{
				if(choose_b.isSelected())
				{
				    tt_b.setEditable(true);
				    tt_a.setEditable(false);
				    tt_s.setEditable(false);
				}
				else if(choose_a.isSelected())
				{
					tt_b.setEditable(false);
					tt_a.setEditable(true);
					tt_s.setEditable(false);
				}
				else if(choose_s.isSelected())
				{
					tt_b.setEditable(false);
					tt_a.setEditable(false);
					tt_s.setEditable(true);
				}
			}
		}
		
		public class Actionhhander implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0)
			{	
				if(choose_a.isSelected())
				{
					try
					{
						String sa=tt_a.getText();
						final double a=Double.parseDouble(sa);
						tt_a.setEditable(false);
						if(a>0)
						{	
							//绘制图象
							area=new JPanel()
							{
								protected void paintComponent(Graphics g)
								{
									Actionhander use=new Actionhander();
									super.paintComponent(g);

									//画x轴
									g.drawLine(20,270,20,20);
									g.drawString("s",325,280);
									//画y轴
									g.drawLine(20,270,320,270);
									g.drawString("B",12,20);
									//画函数图像
									for(int s=1;s<=30;s++)
									{
										double B=use.B(s,a);
										g.drawString(".",20+10*s,(int)(270-250*B));
										g.drawLine(20+10*s, (int)(270-250*B), 20+10*s, 270);
									}
								}
							};
							area.setLayout(new BorderLayout());
							cc.add(area);
							area.setBounds(10, 70, 365, 285);
						}
						else
						{
							JOptionPane.showMessageDialog(PaintFunction.this, "a必须大于0!");
							tt_a.setText("");
						}
					}
					catch(Exception e)
					{
						//非法输入
						JOptionPane.showMessageDialog(PaintFunction.this, "输入非法!请重新输入!");
						tt_a.setText("");
					}
				}
				else if(choose_b.isSelected())
				{
					try
					{
						String sb=tt_b.getText();
						final double b=Double.parseDouble(sb);
						tt_b.setEditable(false);
						if(b>0&&b<1)
						{	
							//绘制图象
							area=new JPanel()
							{
								double a1=0,a2=1;
								double a=0;
								double point=1;
								
								protected void paintComponent(Graphics g)
								{
									Actionhander use=new Actionhander();
									super.paintComponent(g);
									//画x轴
									g.drawLine(20,270,20,20);
									g.drawString("a",325,280);
									//画y轴
									g.drawLine(20,270,320,270);
									g.drawString("s",12,20);
									//画函数图像
									for(int s=1;s<=2;s++)
									{
										for(int i=0;i<=4;i++)
										{
											while(!(use.B(s,a1)<=b&&use.B(s,a2)>=b))
											{
												a1=a1+point;a2=a2+point;	
											}
											a=a1;
											point=point/10;
											a1=a1;a2=a1+point;
										}
										g.drawString(".",20+10*s,(int)(270-a));
										g.drawLine(20+10*s, (int)(270-a), 20+10*s, 270);
									}
								}
							};
							area.setLayout(new BorderLayout());
							cc.add(area);
							area.setBounds(10, 70, 365, 285);
						}
						else
						{
							JOptionPane.showMessageDialog(PaintFunction.this, "B必须为0~1(不包含0和1)之间的数！");
							tt_b.setText("");
						}
					}
					catch(Exception e)
					{
						//非法输入
						JOptionPane.showMessageDialog(PaintFunction.this, "输入非法!请重新输入!");
						tt_b.setText("");
					}
				}
				else if(choose_s.isSelected())
				{
					try
					{					
						String ss=tt_s.getText();
						final int s=Integer.parseInt(ss);
						tt_s.setEditable(false);
						if(s>0)
						{	
							//绘制图象
							area=new JPanel()
							{
								protected void paintComponent(Graphics g)
								{
									Actionhander use=new Actionhander();
									super.paintComponent(g);
									//画x轴
									g.drawLine(20,270,20,20);
									g.drawString("a",325,280);
									//画y轴
									g.drawLine(20,270,320,270);
									g.drawString("B",12,20);
									//画函数图像
									for(double a=0.1;a<=30;a=a+0.1)
									{
										double B=use.B(s,a);
										g.drawString(".",(int) (20+10*a),(int)(270-250*B));
									}
								}
							};
							area.setLayout(new BorderLayout());
							cc.add(area);
							area.setBounds(10, 70, 365, 285);
						}
						else
						{
							JOptionPane.showMessageDialog(PaintFunction.this, "s必须为大于零的整数!");
							tt_s.setText("");
						}
					}
					catch(Exception e)
					{
						//非法输入
						JOptionPane.showMessageDialog(PaintFunction.this, "输入非法!请重新输入!");
						tt_s.setText("");
					}

				}
			}
		}

	}

}

