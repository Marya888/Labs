import java.awt.Color;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import static java.lang.Math.E;
import static java.lang.Math.log;
import static java.lang.Math.pow;

public class Chart extends JFrame {
    private static final long serialVersionUID = 6294689542092367723L;
    private List<Double> list_x,list_y;
    private Map<String, Double> map;
    XYSeriesCollection dataset = new XYSeriesCollection();

    public Chart(String title, List<Double> list_x, List<Double> list_y, Map<String, Double> map) {
        super(title);
        this.list_x=list_x;
        this.list_y=list_y;
        this.map=map;
    }

    public void start(){
        fill(0);
        XYDataset dataset123 = end();

        // Create chart
        JFreeChart chart = ChartFactory.createScatterPlot("", "X-Axis", "Y-Axis", dataset123);

        //Changes background color
        XYPlot plot = (XYPlot)chart.getPlot();
        plot.setBackgroundPaint(new Color(0,0,0));

        // Create Panel
        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }
    
    private XYDataset end(){
        return dataset;
    }

    public void fill(int type_name){
        // Create dataset
        XYSeries series=null;
        if(type_name==0) {
            series = new XYSeries("Заданные точки");
        }else if(type_name==1) {
            series = new XYSeries("Линейная регрессия");
        }else if(type_name==2){
            series = new XYSeries("Квадратичная регрессия");
        }else if(type_name==3){
            series = new XYSeries("Экспоненциальная регрессия");
        }else if(type_name==4){
            series = new XYSeries("Логарифмическая регрессия");
        }else if(type_name==5){
            series = new XYSeries("Степенная регрессия");
        }

        if(type_name==0) {
            for (int i = 0; i < list_x.size(); i++) {
                series.add(list_x.get(i), list_y.get(i));
            }
        }else{
            for(double x=list_x.get(0); x<list_x.get(list_x.size()-1)+1; x+=0.01) {
                if(type_name==1) {
                    series.add(x, map.get("a_for_linear")*x+map.get("b_for_linear"));
                }else if(type_name==2){
                    series.add(x, map.get("a_for_polynomial")+map.get("b_for_polynomial")*x+map.get("c_for_polynomial")*x*x);
                }else if(type_name==3){
                    series.add(x, map.get("a_for_exponential")*pow(E,(map.get("b_for_exponential")*x)));
                }else if(type_name==4){
                    series.add(x, map.get("a_for_logarithmic")+map.get("b_for_logarithmic")*log(x));
                }else if(type_name==5){
                    series.add(x, map.get("a_for_power")*pow(x,map.get("b_for_power")));
                }
            }
        }

        dataset.addSeries(series);
    }
}
