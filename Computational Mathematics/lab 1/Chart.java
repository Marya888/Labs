import java.awt.Color;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Chart extends JFrame {
	  private static final long serialVersionUID = 6294689542092367723L;
	  private double a,b;
	  
	  public Chart(String title,double a,double b) {
	    super(title);
	    this.a=a;
	    this.b=b;

	    // Create dataset
	    XYDataset dataset = createDataset();

	    // Create chart
	    JFreeChart chart = ChartFactory.createScatterPlot("", "X-Axis", "Y-Axis", dataset);
	    
	    //Changes background color
	    XYPlot plot = (XYPlot)chart.getPlot();
	    plot.setBackgroundPaint(new Color(0,0,0));
	    
	    // Create Panel
	    ChartPanel panel = new ChartPanel(chart);
	    setContentPane(panel);
	  }

	  private XYDataset createDataset() {
	    XYSeriesCollection dataset = new XYSeriesCollection();

	    //Boys (Age,weight) series
	    XYSeries series = new XYSeries("кривая");
	   
	    for(double x = a-(b-a)*0.1;x<b+(b-a)*0.1;x=x+0.01) {
	    	series.add(x, 2.335*x*x*x+3.98*x*x-4.52*x-3.11);
	    }
	    

	    dataset.addSeries(series);
	    
	    return dataset;
	  }
}
