package charley.wu.performance;

/**
 * 运行时方差
 * <p>https://www.johndcook.com/blog/standard_deviation/<p/>
 *
 * @author Charley Wu
 * @since 2020/3/2
 */
public class RunningVariance {

  private int n = 0; // 样本个数
  private double mk; // 平均数
  private double sk; // 方差

  public RunningVariance(int n, double mk, double sk) {
    this.n = n;
    this.mk = mk;
    this.sk = sk;
  }

  public void addSample(double x) {
    n++;

    if (n == 1) {
      mk = x;
      sk = 0.0;
    } else {
      double diff = x - mk;
      mk += diff / n;
      sk += diff * (x - mk);
    }
  }

  public void removeSample(double x) {
    if (n == 0) {
      throw new IllegalStateException();
    }
    n--;
    if (n == 0) {
      mk = 0.0;
      sk = 0.0;
    } else {
      double oldMk = mk;
      mk = (n * oldMk - x) / (n - 1);
      sk -= (x - mk) * (x - oldMk);
    }
  }

  public double slide(double k1, double kn) {
    double mk1 = (n * mk - k1) / (n - 1);
    double mkn = mk + (kn - k1) / n;
    double s1 = sk - (k1 - mk1) * (k1 - mk);
    double sn = s1 + (kn - mk1) * (kn - mkn);

    mk = mkn;
    sk = sn;

    return calculateVar();
  }

  private double calculateVar() {
    return n > 1 ? sk / (n - 1) : 0.0;
  }

  public int getN() {
    return n;
  }

  public double getMk() {
    return mk;
  }

  public double getSk() {
    return sk;
  }
}
