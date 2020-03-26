package charley.wu.performance;

/**
 * Desc...
 *
 * @author Charley Wu
 * @since 2020/3/26
 */
public class PerformanceTester {

  private static double[] values = {0.33, 1.33, 0.27333, 0.3, 0.501, 0.444, 0.44, 0.34496, 0.33,
      0.3, 0.292, 0.667, 0.431, 0.124, 0.766, 0.8182, 0.4986, 1.24, 0.7214, 0.2352, 0.76874,
      0.62349, 0.912, 0.52, 0.92, 0.33, 1.33, 0.27333, 0.3, 0.501, 0.444, 0.44, 0.34496, 0.33, 0.3,
      0.292, 0.667, 0.431, 0.124, 0.766, 0.8182, 0.4986, 1.24, 0.7214, 0.2352, 0.76874, 0.62349,
      0.912, 0.52, 0.92, 0.292, 0.667, 0.431, 0.124, 0.766, 0.8182, 0.4986, 0.292, 0.667, 0.431,
      0.124, 0.766, 0.8182, 0.4986};

  private static int n = 1000000000;

  public void testStandardVariance() {
    long start = System.currentTimeMillis();
    for (int i = 0; i < n; i++) {
      StandardVariance sv = new StandardVariance(values);
      sv.calculate();
    }
    long cost = System.currentTimeMillis() - start;
    System.out.println("StandardVariance => total cost:[" + cost + "ms]");
  }

  public void testRunningVariance() {
    RunningVariance prepare = new RunningVariance(0, 0.00, 0.00);
    for (double v : values) {
      prepare.addSample(v);
    }
    int count = prepare.getN();
    double mk = prepare.getMk();
    double sk = prepare.getSk();

    long start = System.currentTimeMillis();
    for (int i = 0; i < n; i++) {
      RunningVariance rv = new RunningVariance(count, mk, sk);
      double v = values[i & (64 - 1)];
      rv.slide(v, v);
    }
    long cost = System.currentTimeMillis() - start;
    System.out.println("RunningVariance  => total cost:[" + cost + "ms]");
  }

  public static void main(String[] args) {
    PerformanceTester tester = new PerformanceTester();
    tester.testStandardVariance();
    tester.testRunningVariance();
  }
}
