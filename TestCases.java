/**
 * TestCases.java - added test cases for scorpion simulation
 * 
 * 16 October 2018
 * Phuong Truong <thphuong@bu.edu>
 * CS480
 */


import java.util.HashMap;
import java.util.Map;

/**
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class TestCases extends CyclicIterator<Map<String, Angled>> {

  Map<String, Angled> test0() {
    return this.test0;
  }

  private final Map<String, Angled> test0;

  @SuppressWarnings("unchecked")
  TestCases() {
    this.test0 = new HashMap<String, Angled>();
    final Map<String, Angled> test1 = new HashMap<String, Angled>();
    final Map<String, Angled> test2 = new HashMap<String, Angled>();
    final Map<String, Angled> test3 = new HashMap<String, Angled>();
    final Map<String, Angled> test4 = new HashMap<String, Angled>();
    final Map<String, Angled> test5 = new HashMap<String, Angled>();

    super.add(test0, test1, test2, test3, test4, test5);

    // the body do not change through any of the test cases
    
    test0.put(PA2.BODY_NAME, new BaseAngled(0, 0, 0));
    
    //test0 case
    test0.put(PA2.LEG1_DISTAL_NAME, new BaseAngled(60, 0, 0));
    test0.put(PA2.LEG1_MIDDLE_NAME, new BaseAngled(-110, 0, 0));
    test0.put(PA2.LEG1_BODY_NAME, new BaseAngled(50, 0, 0));
    test0.put(PA2.LEG2_DISTAL_NAME, new BaseAngled(60, 0, 0));
    test0.put(PA2.LEG2_MIDDLE_NAME, new BaseAngled(-110, 0, 0));
    test0.put(PA2.LEG2_BODY_NAME, new BaseAngled(50, 0, 0));
    test0.put(PA2.LEG3_DISTAL_NAME, new BaseAngled(60, 0, 0));
    test0.put(PA2.LEG3_MIDDLE_NAME, new BaseAngled(-110, 0, 0));
    test0.put(PA2.LEG3_BODY_NAME, new BaseAngled(50, 0, 0));
    test0.put(PA2.LEG4_DISTAL_NAME, new BaseAngled(60, 0, 0));
    test0.put(PA2.LEG4_MIDDLE_NAME, new BaseAngled(-110, 0, 0));
    test0.put(PA2.LEG4_BODY_NAME, new BaseAngled(50, 0, 0));
    test0.put(PA2.LEG5_DISTAL_NAME, new BaseAngled(-60, 0, 0));
    test0.put(PA2.LEG5_MIDDLE_NAME, new BaseAngled(110, 0, 0));
    test0.put(PA2.LEG5_BODY_NAME, new BaseAngled(-50, 0, 0));
    test0.put(PA2.LEG6_DISTAL_NAME, new BaseAngled(-60, 0, 0));
    test0.put(PA2.LEG6_MIDDLE_NAME, new BaseAngled(110, 0, 0));
    test0.put(PA2.LEG6_BODY_NAME, new BaseAngled(-50, 0, 0));
    test0.put(PA2.LEG7_DISTAL_NAME, new BaseAngled(-60, 0, 0));
    test0.put(PA2.LEG7_MIDDLE_NAME, new BaseAngled(110, 0, 0));
    test0.put(PA2.LEG7_BODY_NAME, new BaseAngled(-50, 0, 0));
    test0.put(PA2.LEG8_DISTAL_NAME, new BaseAngled(-60, 0, 0));
    test0.put(PA2.LEG8_MIDDLE_NAME, new BaseAngled(110, 0, 0));
    test0.put(PA2.LEG8_BODY_NAME, new BaseAngled(-50, 0, 0));
    test0.put(PA2.LEFT_CLAW_JOINT3_NAME, new BaseAngled(0, -40, 0));
    test0.put(PA2.LEFT_CLAW_JOINT2_NAME, new BaseAngled(0, -100, 0));
    test0.put(PA2.LEFT_CLAW_JOINT1_NAME, new BaseAngled(0, 0, 0));
    test0.put(PA2.RIGHT_CLAW_JOINT3_NAME, new BaseAngled(0, 40, 0));
    test0.put(PA2.RIGHT_CLAW_JOINT2_NAME, new BaseAngled(0, 100, 0));
    test0.put(PA2.RIGHT_CLAW_JOINT1_NAME, new BaseAngled(0, 0, 0));
    test0.put(PA2.STINGER_NAME, new BaseAngled(0, -75, 0));
    test0.put(PA2.TAIL_JOINT2_NAME, new BaseAngled(0, -70, 0));
    test0.put(PA2.TAIL_JOINT1_NAME, new BaseAngled(90, 40, 0));
    
    //test1 case
    test1.put(PA2.LEG1_DISTAL_NAME, new BaseAngled(60, 0, 0));
    test1.put(PA2.LEG1_MIDDLE_NAME, new BaseAngled(-60, 0, 0));
    test1.put(PA2.LEG1_BODY_NAME, new BaseAngled(30, 0, 0));
    test1.put(PA2.LEG2_DISTAL_NAME, new BaseAngled(60, 0, 0));
    test1.put(PA2.LEG2_MIDDLE_NAME, new BaseAngled(-60, 0, 0));
    test1.put(PA2.LEG2_BODY_NAME, new BaseAngled(30, 0, 0));
    test1.put(PA2.LEG3_DISTAL_NAME, new BaseAngled(60, 0, 0));
    test1.put(PA2.LEG3_MIDDLE_NAME, new BaseAngled(-60, 0, 0));
    test1.put(PA2.LEG3_BODY_NAME, new BaseAngled(30, 0, 0));
    test1.put(PA2.LEG4_DISTAL_NAME, new BaseAngled(60, 0, 0));
    test1.put(PA2.LEG4_MIDDLE_NAME, new BaseAngled(-60, 0, 0));
    test1.put(PA2.LEG4_BODY_NAME, new BaseAngled(30, 0, 0));
    test1.put(PA2.LEG5_DISTAL_NAME, new BaseAngled(-60, 0, 0));
    test1.put(PA2.LEG5_MIDDLE_NAME, new BaseAngled(150, 0, 0));
    test1.put(PA2.LEG5_BODY_NAME, new BaseAngled(-70, 0, 0));
    test1.put(PA2.LEG6_DISTAL_NAME, new BaseAngled(-60, 0, 0));
    test1.put(PA2.LEG6_MIDDLE_NAME, new BaseAngled(150, 0, 0));
    test1.put(PA2.LEG6_BODY_NAME, new BaseAngled(-70, 0, 0));
    test1.put(PA2.LEG7_DISTAL_NAME, new BaseAngled(-60, 0, 0));
    test1.put(PA2.LEG7_MIDDLE_NAME, new BaseAngled(150, 0, 0));
    test1.put(PA2.LEG7_BODY_NAME, new BaseAngled(-70, 0, 0));
    test1.put(PA2.LEG8_DISTAL_NAME, new BaseAngled(-60, 0, 0));
    test1.put(PA2.LEG8_MIDDLE_NAME, new BaseAngled(150, 0, 0));
    test1.put(PA2.LEG8_BODY_NAME, new BaseAngled(-70, 0, 0));
    test1.put(PA2.LEFT_CLAW_JOINT3_NAME, new BaseAngled(0, -40, 0));
    test1.put(PA2.LEFT_CLAW_JOINT2_NAME, new BaseAngled(0, -100, 0));
    test1.put(PA2.LEFT_CLAW_JOINT1_NAME, new BaseAngled(0, 0, 0));
    test1.put(PA2.RIGHT_CLAW_JOINT3_NAME, new BaseAngled(0, 40, 0));
    test1.put(PA2.RIGHT_CLAW_JOINT2_NAME, new BaseAngled(0, 100, 0));
    test1.put(PA2.RIGHT_CLAW_JOINT1_NAME, new BaseAngled(0, 0, 0));
    test1.put(PA2.STINGER_NAME, new BaseAngled(0, -75, 0));
    test1.put(PA2.TAIL_JOINT2_NAME, new BaseAngled(0, -50, 0));
    test1.put(PA2.TAIL_JOINT1_NAME, new BaseAngled(90, 45, 0));
    
    
    //test2 case
    test2.put(PA2.LEG1_DISTAL_NAME, new BaseAngled(60, 0, 0));
    test2.put(PA2.LEG1_MIDDLE_NAME, new BaseAngled(-150, 0, 0));
    test2.put(PA2.LEG1_BODY_NAME, new BaseAngled(70, 0, 0));
    test2.put(PA2.LEG2_DISTAL_NAME, new BaseAngled(60, 0, 0));
    test2.put(PA2.LEG2_MIDDLE_NAME, new BaseAngled(-150, 0, 0));
    test2.put(PA2.LEG2_BODY_NAME, new BaseAngled(70, 0, 0));
    test2.put(PA2.LEG3_DISTAL_NAME, new BaseAngled(60, 0, 0));
    test2.put(PA2.LEG3_MIDDLE_NAME, new BaseAngled(-150, 0, 0));
    test2.put(PA2.LEG3_BODY_NAME, new BaseAngled(70, 0, 0));
    test2.put(PA2.LEG4_DISTAL_NAME, new BaseAngled(60, 0, 0));
    test2.put(PA2.LEG4_MIDDLE_NAME, new BaseAngled(-150, 0, 0));
    test2.put(PA2.LEG4_BODY_NAME, new BaseAngled(70, 0, 0));
    test2.put(PA2.LEG5_DISTAL_NAME, new BaseAngled(-60, 0, 0));
    test2.put(PA2.LEG5_MIDDLE_NAME, new BaseAngled(60, 0, 0));
    test2.put(PA2.LEG5_BODY_NAME, new BaseAngled(-30, 0, 0));
    test2.put(PA2.LEG6_DISTAL_NAME, new BaseAngled(-60, 0, 0));
    test2.put(PA2.LEG6_MIDDLE_NAME, new BaseAngled(60, 0, 0));
    test2.put(PA2.LEG6_BODY_NAME, new BaseAngled(-30, 0, 0));
    test2.put(PA2.LEG7_DISTAL_NAME, new BaseAngled(-60, 0, 0));
    test2.put(PA2.LEG7_MIDDLE_NAME, new BaseAngled(60, 0, 0));
    test2.put(PA2.LEG7_BODY_NAME, new BaseAngled(-30, 0, 0));
    test2.put(PA2.LEG8_DISTAL_NAME, new BaseAngled(-60, 0, 0));
    test2.put(PA2.LEG8_MIDDLE_NAME, new BaseAngled(60, 0, 0));
    test2.put(PA2.LEG8_BODY_NAME, new BaseAngled(-30, 0, 0));
    test2.put(PA2.LEFT_CLAW_JOINT3_NAME, new BaseAngled(0, -40, 0));
    test2.put(PA2.LEFT_CLAW_JOINT2_NAME, new BaseAngled(0, -100, 0));
    test2.put(PA2.LEFT_CLAW_JOINT1_NAME, new BaseAngled(0, 0, 0));
    test2.put(PA2.RIGHT_CLAW_JOINT3_NAME, new BaseAngled(0, 40, 0));
    test2.put(PA2.RIGHT_CLAW_JOINT2_NAME, new BaseAngled(0, 100, 0));
    test2.put(PA2.RIGHT_CLAW_JOINT1_NAME, new BaseAngled(0, 0, 0));
    test2.put(PA2.STINGER_NAME, new BaseAngled(0, -75, 0));
    test2.put(PA2.TAIL_JOINT2_NAME, new BaseAngled(0, -50, 0));
    test2.put(PA2.TAIL_JOINT1_NAME, new BaseAngled(90, 45, 0));
    
    //test3 case
    test3.put(PA2.LEG1_DISTAL_NAME, new BaseAngled(60, 0, 0));
    test3.put(PA2.LEG1_MIDDLE_NAME, new BaseAngled(-150, 0, 0));
    test3.put(PA2.LEG1_BODY_NAME, new BaseAngled(70, 0, 0));
    test3.put(PA2.LEG2_DISTAL_NAME, new BaseAngled(60, 0, 0));
    test3.put(PA2.LEG2_MIDDLE_NAME, new BaseAngled(-150, 0, 0));
    test3.put(PA2.LEG2_BODY_NAME, new BaseAngled(70, 0, 0));
    test3.put(PA2.LEG3_DISTAL_NAME, new BaseAngled(60, 0, 0));
    test3.put(PA2.LEG3_MIDDLE_NAME, new BaseAngled(-150, 0, 0));
    test3.put(PA2.LEG3_BODY_NAME, new BaseAngled(70, 0, 0));
    test3.put(PA2.LEG4_DISTAL_NAME, new BaseAngled(60, 0, 0));
    test3.put(PA2.LEG4_MIDDLE_NAME, new BaseAngled(-150, 0, 0));
    test3.put(PA2.LEG4_BODY_NAME, new BaseAngled(70, 0, 0));
    test3.put(PA2.LEG5_DISTAL_NAME, new BaseAngled(-60, 0, 0));
    test3.put(PA2.LEG5_MIDDLE_NAME, new BaseAngled(150, 0, 0));
    test3.put(PA2.LEG5_BODY_NAME, new BaseAngled(-70, 0, 0));
    test3.put(PA2.LEG6_DISTAL_NAME, new BaseAngled(-60, 0, 0));
    test3.put(PA2.LEG6_MIDDLE_NAME, new BaseAngled(150, 0, 0));
    test3.put(PA2.LEG6_BODY_NAME, new BaseAngled(-70, 0, 0));
    test3.put(PA2.LEG7_DISTAL_NAME, new BaseAngled(-60, 0, 0));
    test3.put(PA2.LEG7_MIDDLE_NAME, new BaseAngled(150, 0, 0));
    test3.put(PA2.LEG7_BODY_NAME, new BaseAngled(-70, 0, 0));
    test3.put(PA2.LEG8_DISTAL_NAME, new BaseAngled(-60, 0, 0));
    test3.put(PA2.LEG8_MIDDLE_NAME, new BaseAngled(150, 0, 0));
    test3.put(PA2.LEG8_BODY_NAME, new BaseAngled(-70, 0, 0));
    test3.put(PA2.LEFT_CLAW_JOINT3_NAME, new BaseAngled(0, -40, 0));
    test3.put(PA2.LEFT_CLAW_JOINT2_NAME, new BaseAngled(0, -100, 0));
    test3.put(PA2.LEFT_CLAW_JOINT1_NAME, new BaseAngled(0, 0, 0));
    test3.put(PA2.RIGHT_CLAW_JOINT3_NAME, new BaseAngled(0, 40, 0));
    test3.put(PA2.RIGHT_CLAW_JOINT2_NAME, new BaseAngled(0, 100, 0));
    test3.put(PA2.RIGHT_CLAW_JOINT1_NAME, new BaseAngled(0, 0, 0));
    test3.put(PA2.STINGER_NAME, new BaseAngled(0, -75, 0));
    test3.put(PA2.TAIL_JOINT2_NAME, new BaseAngled(0, -110, 0));
    test3.put(PA2.TAIL_JOINT1_NAME, new BaseAngled(90, 20, 0));
    
    //test4 case
    test4.put(PA2.LEG1_DISTAL_NAME, new BaseAngled(60, 0, 0));
    test4.put(PA2.LEG1_MIDDLE_NAME, new BaseAngled(-150, 0, 0));
    test4.put(PA2.LEG1_BODY_NAME, new BaseAngled(70, 0, 0));
    test4.put(PA2.LEG2_DISTAL_NAME, new BaseAngled(60, 0, 0));
    test4.put(PA2.LEG2_MIDDLE_NAME, new BaseAngled(-150, 0, 0));
    test4.put(PA2.LEG2_BODY_NAME, new BaseAngled(70, 0, 0));
    test4.put(PA2.LEG3_DISTAL_NAME, new BaseAngled(60, 0, 0));
    test4.put(PA2.LEG3_MIDDLE_NAME, new BaseAngled(-150, 0, 0));
    test4.put(PA2.LEG3_BODY_NAME, new BaseAngled(70, 0, 0));
    test4.put(PA2.LEG4_DISTAL_NAME, new BaseAngled(60, 0, 0));
    test4.put(PA2.LEG4_MIDDLE_NAME, new BaseAngled(-150, 0, 0));
    test4.put(PA2.LEG4_BODY_NAME, new BaseAngled(70, 0, 0));
    test4.put(PA2.LEG5_DISTAL_NAME, new BaseAngled(-60, 0, 0));
    test4.put(PA2.LEG5_MIDDLE_NAME, new BaseAngled(150, 0, 0));
    test4.put(PA2.LEG5_BODY_NAME, new BaseAngled(-70, 0, 0));
    test4.put(PA2.LEG6_DISTAL_NAME, new BaseAngled(-60, 0, 0));
    test4.put(PA2.LEG6_MIDDLE_NAME, new BaseAngled(150, 0, 0));
    test4.put(PA2.LEG6_BODY_NAME, new BaseAngled(-70, 0, 0));
    test4.put(PA2.LEG7_DISTAL_NAME, new BaseAngled(-60, 0, 0));
    test4.put(PA2.LEG7_MIDDLE_NAME, new BaseAngled(150, 0, 0));
    test4.put(PA2.LEG7_BODY_NAME, new BaseAngled(-70, 0, 0));
    test4.put(PA2.LEG8_DISTAL_NAME, new BaseAngled(-60, 0, 0));
    test4.put(PA2.LEG8_MIDDLE_NAME, new BaseAngled(150, 0, 0));
    test4.put(PA2.LEG8_BODY_NAME, new BaseAngled(-70, 0, 0));
    test4.put(PA2.LEFT_CLAW_JOINT3_NAME, new BaseAngled(0, -40, 0));
    test4.put(PA2.LEFT_CLAW_JOINT2_NAME, new BaseAngled(0, -130, 0));
    test4.put(PA2.LEFT_CLAW_JOINT1_NAME, new BaseAngled(0, 40, 0));
    test4.put(PA2.RIGHT_CLAW_JOINT3_NAME, new BaseAngled(0, 40, 0));
    test4.put(PA2.RIGHT_CLAW_JOINT2_NAME, new BaseAngled(0, 130, 0));
    test4.put(PA2.RIGHT_CLAW_JOINT1_NAME, new BaseAngled(0, -40, 0));
    test4.put(PA2.STINGER_NAME, new BaseAngled(0, -75, 0));
    test4.put(PA2.TAIL_JOINT2_NAME, new BaseAngled(0, -75, 0));
    test4.put(PA2.TAIL_JOINT1_NAME, new BaseAngled(90, 20, 0));
    
    //test5 case
    test5.put(PA2.LEG1_DISTAL_NAME, new BaseAngled(60, 0, 0));
    test5.put(PA2.LEG1_MIDDLE_NAME, new BaseAngled(-60, 0, 0));
    test5.put(PA2.LEG1_BODY_NAME, new BaseAngled(30, 0, 0));
    test5.put(PA2.LEG2_DISTAL_NAME, new BaseAngled(60, 0, 0));
    test5.put(PA2.LEG2_MIDDLE_NAME, new BaseAngled(-60, 0, 0));
    test5.put(PA2.LEG2_BODY_NAME, new BaseAngled(30, 0, 0));
    test5.put(PA2.LEG3_DISTAL_NAME, new BaseAngled(60, 0, 0));
    test5.put(PA2.LEG3_MIDDLE_NAME, new BaseAngled(-60, 0, 0));
    test5.put(PA2.LEG3_BODY_NAME, new BaseAngled(30, 0, 0));
    test5.put(PA2.LEG4_DISTAL_NAME, new BaseAngled(60, 0, 0));
    test5.put(PA2.LEG4_MIDDLE_NAME, new BaseAngled(-60, 0, 0));
    test5.put(PA2.LEG4_BODY_NAME, new BaseAngled(30, 0, 0));
    test5.put(PA2.LEG5_DISTAL_NAME, new BaseAngled(-60, 0, 0));
    test5.put(PA2.LEG5_MIDDLE_NAME, new BaseAngled(60, 0, 0));
    test5.put(PA2.LEG5_BODY_NAME, new BaseAngled(-30, 0, 0));
    test5.put(PA2.LEG6_DISTAL_NAME, new BaseAngled(-60, 0, 0));
    test5.put(PA2.LEG6_MIDDLE_NAME, new BaseAngled(60, 0, 0));
    test5.put(PA2.LEG6_BODY_NAME, new BaseAngled(-30, 0, 0));
    test5.put(PA2.LEG7_DISTAL_NAME, new BaseAngled(-60, 0, 0));
    test5.put(PA2.LEG7_MIDDLE_NAME, new BaseAngled(60, 0, 0));
    test5.put(PA2.LEG7_BODY_NAME, new BaseAngled(-30, 0, 0));
    test5.put(PA2.LEG8_DISTAL_NAME, new BaseAngled(-60, 0, 0));
    test5.put(PA2.LEG8_MIDDLE_NAME, new BaseAngled(60, 0, 0));
    test5.put(PA2.LEG8_BODY_NAME, new BaseAngled(-30, 0, 0));
    test5.put(PA2.LEFT_CLAW_JOINT3_NAME, new BaseAngled(0, -10, 0));
    test5.put(PA2.LEFT_CLAW_JOINT2_NAME, new BaseAngled(0, -50, 0));
    test5.put(PA2.LEFT_CLAW_JOINT1_NAME, new BaseAngled(0, -60, 0));
    test5.put(PA2.RIGHT_CLAW_JOINT3_NAME, new BaseAngled(0, 10, 0));
    test5.put(PA2.RIGHT_CLAW_JOINT2_NAME, new BaseAngled(0, 50, 0));
    test5.put(PA2.RIGHT_CLAW_JOINT1_NAME, new BaseAngled(0, 60, 0));
    test5.put(PA2.STINGER_NAME, new BaseAngled(0, -75, 0));
    test5.put(PA2.TAIL_JOINT2_NAME, new BaseAngled(0, -25, 0));
    test5.put(PA2.TAIL_JOINT1_NAME, new BaseAngled(90, 60, 0));
  }
}
