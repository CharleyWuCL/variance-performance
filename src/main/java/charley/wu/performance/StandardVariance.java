package charley.wu.performance;

import org.apache.commons.math3.stat.descriptive.moment.Variance;

/**
 * Desc...
 *
 * @author Charley Wu
 * @since 2020/2/25
 */
public class StandardVariance {

  private double[] samples;

  public StandardVariance(double[] samples) {
    this.samples = samples;
  }

  public double calculate() {
    try {
      Variance variance = new Variance();//方差
      variance.setBiasCorrected(true);//样本方差
      return variance.evaluate(samples);
    } catch (Exception e) {
      e.printStackTrace();
      return 0.0;
    }
  }
}
