/**
 * PA2.java - driver for the insect model simulation
 * 
 * History:
 * 
 * 16 October 2018
 * 
 * - modified for CS480 scorpion simulation
 * 
 * (Phuong Truong <thphuong@bu.edu>)
 * 
 * 19 February 2011
 * 
 * - added documentation
 * 
 * (Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>)
 * 
 * 16 January 2008
 * 
 * - translated from C code by Stan Sclaroff
 * 
 * (Tai-Peng Tian <tiantp@gmail.com>)
 * 
 */

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.awt.GLCanvas;//for new version of gl
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;

import com.jogamp.opengl.util.FPSAnimator;//for new version of gl
import com.jogamp.opengl.util.gl2.GLUT;//for new version of gl

/**
 * The main class which drives the insect model simulation.
 * 
 * @author Tai-Peng Tian <tiantp@gmail.com>
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2008
 */
public class PA2 extends JFrame implements GLEventListener, KeyListener,
    MouseListener, MouseMotionListener {

  /**
   * Each part has a body joint, a middle joint, and a distal joint.
   * 
   * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
   * @since Spring 2011
   */
  private class Part {
    /** The distal joint of this part. */
    private final Component distalJoint;
    /** The list of all the joints in this part. */
    private final List<Component> joints;
    /** The middle joint of this part. */
    private final Component middleJoint;
    /** The body joint of this part. */
    private final Component bodyJoint;

    /**
     * Instantiates this part with the three specified joints.
     * 
     * @param bodyJoint
     *          The body joint of this part.
     * @param middleJoint
     *          The middle joint of this part.
     * @param distalJoint
     *          The distal joint of this part.
     */
    public Part (final Component bodyJoint, final Component middleJoint,
        final Component distalJoint) {
      this.bodyJoint = bodyJoint;
      this.middleJoint = middleJoint;
      this.distalJoint = distalJoint;

      this.joints = Collections.unmodifiableList(Arrays.asList(this.bodyJoint,
          this.middleJoint, this.distalJoint));
    }

    /**
     * Gets the distal joint of this part.
     * 
     * @return The distal joint of this part.
     */
    Component distalJoint() {
      return this.distalJoint;
    }

    /**
     * Gets an unmodifiable view of the list of the joints of this part.
     * 
     * @return An unmodifiable view of the list of the joints of this part.
     */
    List<Component> joints() {
      return this.joints;
    }

    /**
     * Gets the middle joint of this part.
     * 
     * @return The middle joint of this part.
     */
    Component middleJoint() {
      return this.middleJoint;
    }

    /**
     * Gets the body joint of this part.
     * 
     * @return The body joint of this part.
     */
    Component bodyJoint() {
      return this.bodyJoint;
    }
  }

  /** The default width of the created window. */
  public static final int DEFAULT_WINDOW_HEIGHT = 512;
  /** The default height of the created window. */
  public static final int DEFAULT_WINDOW_WIDTH = 512;
  /** The color for components which are selected for rotation. */
  public static final FloatColor ACTIVE_COLOR = FloatColor.RED;
  /** The color for components which are not selected for rotation. */
  public static final FloatColor INACTIVE_COLOR = FloatColor.BROWN;
  /** The initial position of the top level component in the scene. */
  public static final Point3D INITIAL_POSITION = new Point3D(1, 1, 0);
  /** The height of the distal joint on each of the legs. */
  public static final double DISTAL_JOINT_HEIGHT = 0.15;
  /** The height of the middle joint on each of the legs. */
  public static final double MIDDLE_JOINT_HEIGHT = 0.6;
  /** The height of the body joint on each of the legs. */
  public static final double BODY_JOINT_HEIGHT = 0.6;
  /** The angle by which to rotate the joint on user request to rotate. */
  public static final double ROTATION_ANGLE = 2.0;
  /** Randomly generated serial version UID. */
  private static final long serialVersionUID = -7060944143920496524L;
  /** The radius of each joint which comprises the legs. */
  public static final double LEG_RADIUS = 0.03;
  /** The radius of body. */
  public static final double BODY_RADIUS = 0.5;
  /** The radius of each joint on the tail. */
  public static final double TAIL_RADIUS = 0.08;
  /** The height of each joint on the tail. */
  public static final double TAIL_HEIGHT = 0.6;
  /** The height of stinger. */
  public static final double STINGER_HEIGHT = 0.25;
  /** The radius of stinger. */
  public static final double STINGER_RADIUS = 0.01;
  /** The radius of first joint of the claw. */
  public static final double CLAW_JOINT1_RADIUS = 0.06;
  /** The radius of second joint of the claw. */
  public static final double CLAW_JOINT2_RADIUS = 0.08;
  /** The height of first joint of the claw. */
  public static final double CLAW_JOINT1_HEIGHT = 0.3;
  /** The height of second joint of the claw. */
  public static final double CLAW_JOINT2_HEIGHT = 0.25;
  /** The height of third joint of the claw. */
  public static final double CLAW_JOINT3_HEIGHT = 0.2;
  /** The radius of third joint of the claw. */
  public static final double CLAW_JOINT3_RADIUS = 0.05;
  /** The height of third joint of the claw. */
  public static final double CLAW_JOINT4_HEIGHT = 0.25;
  /** The radius of third joint of the claw. */
  public static final double CLAW_JOINT4_RADIUS = 0.06;
  
  
  /**
   * Runs the insect simulation in a single JFrame.
   * 
   * @param args
   *          This parameter is ignored.
   */
  public static void main(final String[] args) {
    new PA2().animator.start();
  }

  /**
   * The animator which controls the framerate at which the canvas is animated.
   */
  final FPSAnimator animator;
  /** The canvas on which we draw the scene. */
  private final GLCanvas canvas;
  /** The capabilities of the canvas. */
  private final GLCapabilities capabilities = new GLCapabilities(null);
  /** The parts on the insect to be modeled. */
  private final Part [] parts;
  /** The OpenGL utility object. */
  private final GLU glu = new GLU();
  /** The OpenGL utility toolkit object. */
  private final GLUT glut = new GLUT();
  /** The body to be modeled. */
  private final Component body;
  /** The last x and y coordinates of the mouse press. */
  private int last_x = 0, last_y = 0;
  /** Whether the world is being rotated. */
  private boolean rotate_world = false;
  /** The axis around which to rotate the selected joints. */
  private Axis selectedAxis = Axis.X;
  /** The set of components which are currently selected for rotation. */
  private final Set<Component> selectedComponents = new HashSet<Component>(18);
  /**
   * The set of parts which have been selected for rotation.
   * 
   * Selecting a joint will only affect the joints in this set of selected
   * parts.
   **/
  private final Set<Part> selectedParts = new HashSet<Part>(5);
  /** Whether the state of the model has been changed. */
  private boolean stateChanged = true;
  /**
   * The top level component in the scene which controls the positioning and
   * rotation of everything in the scene.
   */
  private final Component topLevelComponent;
  /** The quaternion which controls the rotation of the world. */
  private Quaternion viewing_quaternion = new Quaternion();
  /** The set of all components. */
  private final List<Component> components;
 
  public static String TOP_LEVEL_NAME = "top level"; 
  public static String LEG1_BODY_NAME = "leg1 body";
  public static String LEG1_MIDDLE_NAME = "leg1 middle";
  public static String LEG1_DISTAL_NAME = "leg1 distal";
  public static String LEG2_BODY_NAME = "leg2 body";
  public static String LEG2_MIDDLE_NAME = "leg2 middle";
  public static String LEG2_DISTAL_NAME = "leg2 distal";
  public static String LEG3_BODY_NAME = "leg3 body";
  public static String LEG3_MIDDLE_NAME = "leg3 middle";
  public static String LEG3_DISTAL_NAME = "leg3 distal";
  public static String LEG4_BODY_NAME = "leg4 body";
  public static String LEG4_MIDDLE_NAME = "leg4 middle";
  public static String LEG4_DISTAL_NAME = "leg4 distal"; 
  public static String LEG5_BODY_NAME = "leg5 body";
  public static String LEG5_MIDDLE_NAME = "leg5 middle";
  public static String LEG5_DISTAL_NAME = "leg5 distal";
  public static String LEG6_BODY_NAME = "leg6 body";
  public static String LEG6_MIDDLE_NAME = "leg6 middle";
  public static String LEG6_DISTAL_NAME = "leg6 distal";
  public static String LEG7_BODY_NAME = "leg7 body";
  public static String LEG7_MIDDLE_NAME = "leg7 middle";
  public static String LEG7_DISTAL_NAME = "leg7 distal";
  public static String LEG8_BODY_NAME = "leg8 body";
  public static String LEG8_MIDDLE_NAME = "leg8 middle";
  public static String LEG8_DISTAL_NAME = "leg8 distal";
  public static String BODY_NAME = "body";
  public static String TAIL_JOINT1_NAME = "tail joint 1";
  public static String TAIL_JOINT2_NAME = "tail joint 2";
  public static String STINGER_NAME = "stinger";
  public static String LEFT_CLAW_JOINT1_NAME = "left claw joint 1";
  public static String LEFT_CLAW_JOINT2_NAME = "left claw joint 2";
  public static String LEFT_CLAW_JOINT3_NAME = "left claw joint 3";
  public static String LEFT_CLAW_JOINT4_NAME = "left claw joint 4";
  public static String RIGHT_CLAW_JOINT1_NAME = "right claw joint 1";
  public static String RIGHT_CLAW_JOINT2_NAME = "right claw joint 2";
  public static String RIGHT_CLAW_JOINT3_NAME = "right claw joint 3";
  public static String RIGHT_CLAW_JOINT4_NAME = "right claw joint 4";


  /**
   * Initializes the necessary OpenGL objects and adds a canvas to this JFrame.
   */
  public PA2() {
    this.capabilities.setDoubleBuffered(true);

    this.canvas = new GLCanvas(this.capabilities);
    this.canvas.addGLEventListener(this);
    this.canvas.addMouseListener(this);
    this.canvas.addMouseMotionListener(this);
    this.canvas.addKeyListener(this);
    // this is true by default, but we just add this line to be explicit
    this.canvas.setAutoSwapBufferMode(true);
    this.getContentPane().add(this.canvas);

    // refresh the scene at 60 frames per second
    this.animator = new FPSAnimator(this.canvas, 60);

    this.setTitle("CS480/CS680 : Insect Simulator");
    this.setSize(DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);

    // all the distal joints
    final Component distal1 = new Component(new Point3D(0, 0,
        MIDDLE_JOINT_HEIGHT), new RoundedCylinder(LEG_RADIUS,
        DISTAL_JOINT_HEIGHT, this.glut), LEG1_DISTAL_NAME);
    final Component distal2 = new Component(new Point3D(0, 0,
        MIDDLE_JOINT_HEIGHT), new RoundedCylinder(LEG_RADIUS,
        DISTAL_JOINT_HEIGHT, this.glut), LEG2_DISTAL_NAME);
    final Component distal3 = new Component(new Point3D(0, 0,
        MIDDLE_JOINT_HEIGHT), new RoundedCylinder(LEG_RADIUS,
        DISTAL_JOINT_HEIGHT, this.glut), LEG3_DISTAL_NAME);
    final Component distal4 = new Component(new Point3D(0, 0,
        MIDDLE_JOINT_HEIGHT), new RoundedCylinder(LEG_RADIUS,
        DISTAL_JOINT_HEIGHT, this.glut), LEG4_DISTAL_NAME);
    final Component distal5 = new Component(new Point3D(0, 0,
        -MIDDLE_JOINT_HEIGHT), new RoundedCylinder(LEG_RADIUS,
        -DISTAL_JOINT_HEIGHT, this.glut), LEG5_DISTAL_NAME);
    final Component distal6 = new Component(new Point3D(0, 0,
        -MIDDLE_JOINT_HEIGHT), new RoundedCylinder(LEG_RADIUS,
        -DISTAL_JOINT_HEIGHT, this.glut), LEG6_DISTAL_NAME);
    final Component distal7 = new Component(new Point3D(0, 0,
        -MIDDLE_JOINT_HEIGHT), new RoundedCylinder(LEG_RADIUS,
        -DISTAL_JOINT_HEIGHT, this.glut), LEG7_DISTAL_NAME);
    final Component distal8 = new Component(new Point3D(0, 0,
        -MIDDLE_JOINT_HEIGHT), new RoundedCylinder(LEG_RADIUS,
        -DISTAL_JOINT_HEIGHT, this.glut), LEG8_DISTAL_NAME);
    
    //claw joint3, 4
    final Component lclaw3 = new Component(new Point3D(0, 0,
        CLAW_JOINT2_HEIGHT), new RoundedCylinder(CLAW_JOINT3_RADIUS,
        CLAW_JOINT3_HEIGHT, this.glut), LEFT_CLAW_JOINT3_NAME);
    final Component rclaw3 = new Component(new Point3D(0, 0,
        -CLAW_JOINT2_HEIGHT), new RoundedCylinder(CLAW_JOINT3_RADIUS,
        -CLAW_JOINT3_HEIGHT, this.glut), RIGHT_CLAW_JOINT3_NAME);
    
    final Component lclaw4 = new Component(new Point3D(0, 0,
        0), new RoundedCylinder(CLAW_JOINT4_RADIUS,
        CLAW_JOINT4_HEIGHT, this.glut), LEFT_CLAW_JOINT4_NAME);
    final Component rclaw4 = new Component(new Point3D(0, 0,
        0), new RoundedCylinder(CLAW_JOINT4_RADIUS,
        CLAW_JOINT4_HEIGHT, this.glut), RIGHT_CLAW_JOINT4_NAME);
    
    //tail stinger
    final Component tail3 = new Component(new Point3D(0, 0,
        TAIL_HEIGHT), new RoundedCylinder(STINGER_RADIUS,
        STINGER_HEIGHT, this.glut), STINGER_NAME); 
    
    // all the middle joints
    final Component middle1 = new Component(new Point3D(0, 0,
        BODY_JOINT_HEIGHT), new RoundedCylinder(LEG_RADIUS,
        MIDDLE_JOINT_HEIGHT, this.glut), LEG1_MIDDLE_NAME);
    final Component middle2 = new Component(new Point3D(0, 0,
        BODY_JOINT_HEIGHT), new RoundedCylinder(LEG_RADIUS,
        MIDDLE_JOINT_HEIGHT, this.glut), LEG2_MIDDLE_NAME);
    final Component middle3 = new Component(new Point3D(0, 0,
        BODY_JOINT_HEIGHT), new RoundedCylinder(LEG_RADIUS,
        MIDDLE_JOINT_HEIGHT, this.glut), LEG3_MIDDLE_NAME);
    final Component middle4 = new Component(new Point3D(0, 0,
        BODY_JOINT_HEIGHT), new RoundedCylinder(LEG_RADIUS,
        MIDDLE_JOINT_HEIGHT, this.glut), LEG4_MIDDLE_NAME);
    final Component middle5 = new Component(new Point3D(0, 0,
        -BODY_JOINT_HEIGHT), new RoundedCylinder(LEG_RADIUS,
        -MIDDLE_JOINT_HEIGHT, this.glut), LEG5_MIDDLE_NAME);    
    final Component middle6 = new Component(new Point3D(0, 0,
        -BODY_JOINT_HEIGHT), new RoundedCylinder(LEG_RADIUS,
        -MIDDLE_JOINT_HEIGHT, this.glut), LEG6_MIDDLE_NAME);
    final Component middle7 = new Component(new Point3D(0, 0,
        -BODY_JOINT_HEIGHT), new RoundedCylinder(LEG_RADIUS,
        -MIDDLE_JOINT_HEIGHT, this.glut), LEG7_MIDDLE_NAME);
    final Component middle8 = new Component(new Point3D(0, 0,
        -BODY_JOINT_HEIGHT), new RoundedCylinder(LEG_RADIUS,
        -MIDDLE_JOINT_HEIGHT, this.glut), LEG8_MIDDLE_NAME);
    
    //claw joint 2
    final Component lclaw2 = new Component(new Point3D(0, 0,
        CLAW_JOINT1_HEIGHT), new RoundedCylinder(CLAW_JOINT2_RADIUS,
        CLAW_JOINT2_HEIGHT, this.glut), LEFT_CLAW_JOINT2_NAME);
    final Component rclaw2 = new Component(new Point3D(0, 0,
        -CLAW_JOINT1_HEIGHT), new RoundedCylinder(CLAW_JOINT2_RADIUS,
        -CLAW_JOINT2_HEIGHT, this.glut), RIGHT_CLAW_JOINT2_NAME);
    
    //tail joint 2
    final Component tail2 = new Component(new Point3D(0, 0,
        TAIL_HEIGHT), new RoundedCylinder(TAIL_RADIUS,
        TAIL_HEIGHT, this.glut), TAIL_JOINT2_NAME); 
    
    // all the body joints, displaced by various amounts from the body
    final Component body1 = new Component(new Point3D(-0.3, 0, 0.7),
        new RoundedCylinder(LEG_RADIUS, BODY_JOINT_HEIGHT, this.glut),
        LEG1_BODY_NAME);
    final Component body2 = new Component(new Point3D(-.1, 0, 0.75),
        new RoundedCylinder(LEG_RADIUS, BODY_JOINT_HEIGHT, this.glut),
        LEG2_BODY_NAME);
    final Component body3 = new Component(new Point3D(0.1, 0, 0.75),
        new RoundedCylinder(LEG_RADIUS, BODY_JOINT_HEIGHT, this.glut),
        LEG3_BODY_NAME);
    final Component body4 = new Component(new Point3D(0.3, 0, 0.75),
        new RoundedCylinder(LEG_RADIUS, BODY_JOINT_HEIGHT, this.glut),
        LEG4_BODY_NAME);
    final Component body5 = new Component(new Point3D(-0.3, 0, 0.25),
        new RoundedCylinder(LEG_RADIUS, -BODY_JOINT_HEIGHT, this.glut),
        LEG5_BODY_NAME);
    final Component body6 = new Component(new Point3D(-.1, 0, 0.20),
        new RoundedCylinder(LEG_RADIUS, -BODY_JOINT_HEIGHT, this.glut),
        LEG6_BODY_NAME);
    final Component body7 = new Component(new Point3D(0.1, 0, 0.20),
        new RoundedCylinder(LEG_RADIUS, -BODY_JOINT_HEIGHT, this.glut),
        LEG7_BODY_NAME);
    final Component body8 = new Component(new Point3D(0.3, 0, 0.20),
        new RoundedCylinder(LEG_RADIUS, -BODY_JOINT_HEIGHT, this.glut),
        LEG8_BODY_NAME);
    
    //claw joint 1
    final Component lclaw1 = new Component(new Point3D(-0.6, 0, 0.63),
        new RoundedCylinder(CLAW_JOINT1_RADIUS, CLAW_JOINT1_HEIGHT, this.glut),
        LEFT_CLAW_JOINT1_NAME);
    final Component rclaw1 = new Component(new Point3D(-0.6, 0, 0.35),
        new RoundedCylinder(CLAW_JOINT1_RADIUS, -CLAW_JOINT1_HEIGHT, this.glut),
        RIGHT_CLAW_JOINT1_NAME);
    
    //tail joint1
    final Component tail1 = new Component(new Point3D(0.7, 0, 0.5),
        new RoundedCylinder(TAIL_RADIUS, TAIL_HEIGHT, this.glut),
        TAIL_JOINT1_NAME); 

    // put together the limbs for easier selection by keyboard input later on
    this.parts = new Part[] { new Part(body1, middle1, distal1),
        new Part(body2, middle2, distal2),
        new Part(body3, middle3, distal3),
        new Part(body4, middle4, distal4),
        new Part(body5, middle5, distal5),
        new Part(body6, middle6, distal6),
        new Part(body7, middle7, distal7),
        new Part(body8, middle8, distal8),
        new Part(lclaw1, lclaw2, lclaw3),
        new Part(rclaw1, rclaw2, rclaw3),
        new Part(tail1, tail2, tail3)};

    // the body 
    this.body = new Component(new Point3D(-1.5, 0, 0.2), new Body(
        BODY_RADIUS, this.glut), BODY_NAME);
    
    // the top level component which provides an initial position and rotation
    // to the scene (but does not cause anything to be drawn)
    this.topLevelComponent = new Component(INITIAL_POSITION, TOP_LEVEL_NAME);
    
    this.topLevelComponent.addChild(this.body);
    this.body.addChildren(body1, body2, body3, body4, body5, body6, body7, body8, lclaw1, rclaw1, tail1);
    body1.addChild(middle1);
    body2.addChild(middle2);
    body3.addChild(middle3);
    body4.addChild(middle4);
    body5.addChild(middle5);
    body6.addChild(middle6);
    body7.addChild(middle7);
    body8.addChild(middle8);
    lclaw1.addChild(lclaw2);
    rclaw1.addChild(rclaw2);
    middle1.addChild(distal1);
    middle2.addChild(distal2);
    middle3.addChild(distal3);
    middle4.addChild(distal4);
    middle5.addChild(distal5);
    middle6.addChild(distal6);
    middle7.addChild(distal7);
    middle8.addChild(distal8);
    lclaw2.addChild(lclaw3);
    rclaw2.addChild(rclaw3);
    lclaw3.addChild(lclaw4);
    rclaw3.addChild(rclaw4);
    
    
    tail1.addChild(tail2);
    tail2.addChild(tail3);

    //turn the whole insect 
    this.topLevelComponent.rotate(Axis.X, 220);
    this.topLevelComponent.rotate(Axis.Y, -60);
    this.topLevelComponent.rotate(Axis.Z, -10);
    

    //rotate tail
    tail1.rotate(Axis.X, 90);
    tail1.rotate(Axis.Y, 40);
    tail2.rotate(Axis.Y, -70);
    tail3.rotate(Axis.Y, -75); 
    
    //rotate leg 1
    body1.rotate(Axis.X, 50);
    middle1.rotate(Axis.X, -110);
    distal1.rotate(Axis.X, 60);
    
    //rotate leg 2
    body2.rotate(Axis.X, 50);
    middle2.rotate(Axis.X, -110);
    distal2.rotate(Axis.X, 60);
    
    //rotate leg 3
    body3.rotate(Axis.X, 50);
    middle3.rotate(Axis.X, -110);
    distal3.rotate(Axis.X, 60);
    
    //rotate leg 4
    body4.rotate(Axis.X, 50);
    middle4.rotate(Axis.X, -110);
    distal4.rotate(Axis.X, 60);
    
    //rotate leg 5
    body5.rotate(Axis.X, -50);
    middle5.rotate(Axis.X, 110);
    distal5.rotate(Axis.X, -60);
    
    //rotate leg 6
    body6.rotate(Axis.X, -50);
    middle6.rotate(Axis.X, 110);
    distal6.rotate(Axis.X, -60);
    
    //rotate leg 7
    body7.rotate(Axis.X, -50);
    middle7.rotate(Axis.X, 110);
    distal7.rotate(Axis.X, -60);
    
    //rotate leg 8
    body8.rotate(Axis.X, -50);
    middle8.rotate(Axis.X, 110);
    distal8.rotate(Axis.X, -60);

    // rotate the left claw
    lclaw2.rotate(Axis.Y, -100);
    lclaw3.rotate(Axis.Y, -40);
    lclaw4.rotate(Axis.Y, 60);
    
    
    // rotate the right claw
    rclaw2.rotate(Axis.Y, 100);
    rclaw3.rotate(Axis.Y, 40);
    rclaw4.rotate(Axis.Y, 120);
    
    // set rotation limits for the body joints of the legs
    for (final Component bodyJoint : Arrays.asList(body1, body2, body3, body4)) {
      bodyJoint.setXPositiveExtent(50);
      bodyJoint.setXNegativeExtent(-10);
      bodyJoint.setYPositiveExtent(0);
      bodyJoint.setYNegativeExtent(0);
      bodyJoint.setZPositiveExtent(0);
      bodyJoint.setZNegativeExtent(0);
    }
    
    for (final Component bodyJoint : Arrays.asList(body5, body6, body7, body8)) {
      bodyJoint.setXPositiveExtent(10);
      bodyJoint.setXNegativeExtent(-50);
      bodyJoint.setYPositiveExtent(0);
      bodyJoint.setYNegativeExtent(0);
      bodyJoint.setZPositiveExtent(0);
      bodyJoint.setZNegativeExtent(0);
    }
    
    // set rotation limits for the middle joints of the leg
    for (final Component middleJoint : Arrays.asList(middle1, middle2,
        middle3, middle4)) {
      middleJoint.setXPositiveExtent(-30);
      middleJoint.setXNegativeExtent(-120);
      middleJoint.setYPositiveExtent(0);
      middleJoint.setYNegativeExtent(0);
      middleJoint.setZPositiveExtent(0);
      middleJoint.setZNegativeExtent(0);
    }
    
    for (final Component middleJoint : Arrays.asList(middle5, middle6, middle7,
        middle8)) {
      middleJoint.setXPositiveExtent(120);
      middleJoint.setXNegativeExtent(30);
      middleJoint.setYPositiveExtent(0);
      middleJoint.setYNegativeExtent(0);
      middleJoint.setZPositiveExtent(0);
      middleJoint.setZNegativeExtent(0);
    }

    // set rotation limits for the distal joints of the leg
    for (final Component distalJoint : Arrays.asList(distal1, distal2,
        distal3, distal4)) {
      distalJoint.setXPositiveExtent(60);
      distalJoint.setXNegativeExtent(-40);
      distalJoint.setYPositiveExtent(0);
      distalJoint.setYNegativeExtent(0);
      distalJoint.setZPositiveExtent(0);
      distalJoint.setZNegativeExtent(0);
    }
    
    for (final Component distalJoint : Arrays.asList(distal5, distal6, distal7, distal8)) {
      distalJoint.setXPositiveExtent(40);
      distalJoint.setXNegativeExtent(-60);
      distalJoint.setYPositiveExtent(0);
      distalJoint.setYNegativeExtent(0);
      distalJoint.setZPositiveExtent(0);
      distalJoint.setZNegativeExtent(0);
    }

    // set the rotation limits for the left claw
    lclaw1.setXPositiveExtent(0);
    lclaw1.setXNegativeExtent(0);
    lclaw1.setYPositiveExtent(10);
    lclaw1.setYNegativeExtent(-60);
    lclaw1.setZPositiveExtent(0);
    lclaw1.setZNegativeExtent(0);
    lclaw2.setXPositiveExtent(0);
    lclaw2.setXNegativeExtent(0);
    lclaw2.setYPositiveExtent(-50);
    lclaw2.setYNegativeExtent(-110);
    lclaw2.setZPositiveExtent(0);
    lclaw2.setZNegativeExtent(0);
    lclaw3.setXPositiveExtent(0);
    lclaw3.setXNegativeExtent(0);
    lclaw3.setYPositiveExtent(-10);
    lclaw3.setYNegativeExtent(-50);
    lclaw3.setZPositiveExtent(0);
    lclaw3.setZNegativeExtent(0);
    
    
    // set the rotation limits for the right claw
    rclaw1.setXPositiveExtent(0);
    rclaw1.setXNegativeExtent(0);
    rclaw1.setYPositiveExtent(60);
    rclaw1.setYNegativeExtent(-10);
    rclaw1.setZPositiveExtent(0);
    rclaw1.setZNegativeExtent(0);
    rclaw2.setXPositiveExtent(0);
    rclaw2.setXNegativeExtent(0);
    rclaw2.setYPositiveExtent(110);
    rclaw2.setYNegativeExtent(50);
    rclaw2.setZPositiveExtent(0);
    rclaw2.setZNegativeExtent(0);
    rclaw3.setXPositiveExtent(0);
    rclaw3.setXNegativeExtent(0);
    rclaw3.setYPositiveExtent(50);
    rclaw3.setYNegativeExtent(10);
    rclaw3.setZPositiveExtent(0);
    rclaw3.setZNegativeExtent(0);
    
    // set the rotation limits for the tail
    tail1.setXPositiveExtent(0);
    tail1.setXNegativeExtent(90);
    tail1.setYPositiveExtent(60);
    tail1.setYNegativeExtent(20);
    tail1.setZPositiveExtent(0);
    tail1.setZNegativeExtent(0);
    
    tail2.setXPositiveExtent(0);
    tail2.setXNegativeExtent(0);
    tail2.setYPositiveExtent(-25);
    tail2.setYNegativeExtent(-100);
    tail2.setZPositiveExtent(0);
    tail2.setZNegativeExtent(0);
    
    tail3.setXPositiveExtent(0);
    tail3.setXNegativeExtent(0);
    tail3.setYPositiveExtent(-45);
    tail3.setYNegativeExtent(-85);
    tail3.setZPositiveExtent(0);
    tail3.setZNegativeExtent(0);

    

    // create the list of all the components for debugging purposes
    this.components = Arrays.asList(body1, middle1, distal1, body2, middle2,
        distal2, body3, middle3, distal3, body4, middle4, distal4, body5,
        middle5, distal5, body6, middle6, distal6, body7, middle7, distal7,
        body8, middle8, distal8, lclaw1, lclaw2, lclaw3, rclaw1, rclaw2, rclaw3, this.body);
  }

  /**
   * Redisplays the scene containing the insect model.
   * 
   * @param drawable
   *          The OpenGL drawable object with which to create OpenGL models.
   */
  public void display(final GLAutoDrawable drawable) {
    final GL2 gl = (GL2)drawable.getGL();

    // clear the display
    gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

    // from here on affect the model view
    gl.glMatrixMode(GL2.GL_MODELVIEW);

    // start with the identity matrix initially
    gl.glLoadIdentity();

    // rotate the world by the appropriate rotation quaternion
    gl.glMultMatrixf(this.viewing_quaternion.toMatrix(), 0);

    // update the position of the components which need to be updated
    // TODO only need to update the selected and JUST deselected components
    if (this.stateChanged) {
      this.topLevelComponent.update(gl);
      this.stateChanged = false;
    }

    // redraw the components
    this.topLevelComponent.draw(gl);
  }

  /**
   * This method is intentionally unimplemented.
   * 
   * @param drawable
   *          This parameter is ignored.
   * @param modeChanged
   *          This parameter is ignored.
   * @param deviceChanged
   *          This parameter is ignored.
   */
  public void displayChanged(GLAutoDrawable drawable, boolean modeChanged,
      boolean deviceChanged) {
    // intentionally unimplemented
  }

  /**
   * Initializes the scene and model.
   * 
   * @param drawable
   *          {@inheritDoc}
   */
  public void init(final GLAutoDrawable drawable) {
    final GL2 gl = (GL2)drawable.getGL();

    // perform any initialization needed by the insect model
    this.topLevelComponent.initialize(gl);

    // initially draw the scene
    this.topLevelComponent.update(gl);

    // set up for shaded display of the insect
    final float light0_position[] = { 1, 1, 1, 0 };
    final float light0_ambient_color[] = { 0.25f, 0.25f, 0.25f, 1 };
    final float light0_diffuse_color[] = { 1, 1, 1, 1 };

    gl.glPolygonMode(GL.GL_FRONT, GL2.GL_FILL);
    gl.glEnable(GL2.GL_COLOR_MATERIAL);
    gl.glColorMaterial(GL.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE);

    gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
    gl.glShadeModel(GL2.GL_SMOOTH);

    // set up the light source
    gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, light0_position, 0);
    gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, light0_ambient_color, 0);
    gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, light0_diffuse_color, 0);

    // turn lighting and depth buffering on
    gl.glEnable(GL2.GL_LIGHTING);
    gl.glEnable(GL2.GL_LIGHT0);
    gl.glEnable(GL2.GL_DEPTH_TEST);
    gl.glEnable(GL2.GL_NORMALIZE);
  }

  /**
   * Interprets key presses according to the following scheme:
   * 
   * up-arrow, down-arrow: increase/decrease rotation angle
   * 
   * @param key
   *          The key press event object.
   */
  public void keyPressed(final KeyEvent key) {
    switch (key.getKeyCode()) {
    case KeyEvent.VK_KP_UP:
    case KeyEvent.VK_UP:
      for (final Component component : this.selectedComponents) {
        component.rotate(this.selectedAxis, ROTATION_ANGLE);
      }
      this.stateChanged = true;
      break;
    case KeyEvent.VK_KP_DOWN:
    case KeyEvent.VK_DOWN:
      for (final Component component : this.selectedComponents) {
        component.rotate(this.selectedAxis, -ROTATION_ANGLE);
      }
      this.stateChanged = true;
      break;
    default:
      break;
    }
  }

  /**
   * This method is intentionally unimplemented.
   * 
   * @param key
   *          This parameter is ignored.
   */
  public void keyReleased(final KeyEvent key) {
    // intentionally unimplemented
  }

  private final TestCases testCases = new TestCases();

  private void setModelState(final Map<String, Angled> state) {
    this.parts[0].bodyJoint().setAngles(state.get(LEG1_BODY_NAME));
    this.parts[0].middleJoint().setAngles(state.get(LEG1_MIDDLE_NAME));
    this.parts[0].distalJoint().setAngles(state.get(LEG1_DISTAL_NAME));
    this.parts[1].bodyJoint().setAngles(state.get(LEG2_BODY_NAME));
    this.parts[1].middleJoint().setAngles(state.get(LEG2_MIDDLE_NAME));
    this.parts[1].distalJoint().setAngles(state.get(LEG2_DISTAL_NAME));
    this.parts[2].bodyJoint().setAngles(state.get(LEG3_BODY_NAME));
    this.parts[2].middleJoint().setAngles(state.get(LEG3_MIDDLE_NAME));
    this.parts[2].distalJoint().setAngles(state.get(LEG3_DISTAL_NAME));
    this.parts[3].bodyJoint().setAngles(state.get(LEG4_BODY_NAME));
    this.parts[3].middleJoint().setAngles(state.get(LEG4_MIDDLE_NAME));
    this.parts[3].distalJoint().setAngles(state.get(LEG4_DISTAL_NAME));
    this.parts[4].bodyJoint().setAngles(state.get(LEG5_BODY_NAME));
    this.parts[4].middleJoint().setAngles(state.get(LEG5_MIDDLE_NAME));
    this.parts[4].distalJoint().setAngles(state.get(LEG5_DISTAL_NAME));
    this.parts[5].bodyJoint().setAngles(state.get(LEG6_BODY_NAME));
    this.parts[5].middleJoint().setAngles(state.get(LEG6_MIDDLE_NAME));
    this.parts[5].distalJoint().setAngles(state.get(LEG6_DISTAL_NAME));
    this.parts[6].bodyJoint().setAngles(state.get(LEG7_BODY_NAME));
    this.parts[6].middleJoint().setAngles(state.get(LEG7_MIDDLE_NAME));
    this.parts[6].distalJoint().setAngles(state.get(LEG7_DISTAL_NAME));
    this.parts[7].bodyJoint().setAngles(state.get(LEG8_BODY_NAME));
    this.parts[7].middleJoint().setAngles(state.get(LEG8_MIDDLE_NAME));
    this.parts[7].distalJoint().setAngles(state.get(LEG8_DISTAL_NAME));
    this.parts[8].bodyJoint().setAngles(state.get(LEFT_CLAW_JOINT1_NAME));
    this.parts[8].middleJoint().setAngles(state.get(LEFT_CLAW_JOINT2_NAME));
    this.parts[8].distalJoint().setAngles(state.get(LEFT_CLAW_JOINT3_NAME));
    this.parts[9].bodyJoint().setAngles(state.get(RIGHT_CLAW_JOINT1_NAME));
    this.parts[9].middleJoint().setAngles(state.get(RIGHT_CLAW_JOINT2_NAME));
    this.parts[9].distalJoint().setAngles(state.get(RIGHT_CLAW_JOINT3_NAME));
    this.parts[10].bodyJoint().setAngles(state.get(TAIL_JOINT1_NAME));
    this.parts[10].middleJoint().setAngles(state.get(TAIL_JOINT2_NAME));
    this.parts[10].distalJoint().setAngles(state.get(STINGER_NAME));
    
    this.stateChanged = true;
  }

  /**
   * Interprets typed keys according to the following scheme:
   * 
   * a : toggle the first left leg active in rotation
   * 
   * b : toggle the second left leg active in rotation
   * 
   * c : toggle the third left leg active in rotation
   * 
   * d : toggle the fourth left leg active in rotation
   * 
   * A : toggle the fist right leg active in rotation
   * 
   * B : toggle the second right leg active in rotation
   * 
   * C : toggle the third right leg active rotation
   * 
   * D : toggle the fourth right leg active rotation
   * 
   * f : toggle the left claw for rotation
   * 
   * F: toggle the right claw for rotation
   * 
   * X : use the X axis rotation at the active joint(s)
   * 
   * Y : use the Y axis rotation at the active joint(s)
   * 
   * Z : use the Z axis rotation at the active joint(s)
   * 
   * 1 : select first joint
   * 
   * 2 : select middle joint
   * 
   * 3 : select last joint
   * 
   * R : resets the view to the initial rotation
   * 
   * O : prints the angles of the five fingers for debugging purposes
   * 
   * Q, Esc : exits the program
   * 
   */
  public void keyTyped(final KeyEvent key) {
    switch (key.getKeyChar()) {
    case 'Q':
    case 'q':
    case KeyEvent.VK_ESCAPE:
      new Thread() {
        @Override
        public void run() {
          PA2.this.animator.stop();
        }
      }.start();
      System.exit(0);
      break;

    // print the angles of the components
    case 'O':
    case 'o':
      printJoints();
      break;

    // set the state of the hand to the next test case
    case 'P':
    case 'p':
      this.setModelState(this.testCases.next());
      break;

    // set the viewing quaternion to 0 rotation
    case 'R':
    case 'r':
      this.viewing_quaternion.reset();
      this.setModelState(this.testCases.test0());
      break;

    // Toggle which one are affected by the current rotation
    case 'a':
      toggleSelection(this.parts[0]);
      break;
    case 'b':
      toggleSelection(this.parts[1]);
      break;
    case 'c':
      toggleSelection(this.parts[2]);
      break;
    case 'd':
      toggleSelection(this.parts[3]);
      break;
    case 'f':
      toggleSelection(this.parts[8]);
      break;
      
    case 'A':
      toggleSelection(this.parts[4]);
      break;
    case 'B':
      toggleSelection(this.parts[5]);
      break;
    case 'C':
      toggleSelection(this.parts[6]);
      break;
    case 'D':
      toggleSelection(this.parts[7]);
      break;
    case 'F':
      toggleSelection(this.parts[9]);
      break;
      
    case 'T':
    case 't':
      toggleSelection(this.parts[10]);
      break;
      
    // toggle which joints are affected by the current rotation
    case '3':
      for (final Part part : this.selectedParts) {
        toggleSelection(part.distalJoint());
      }
      break;
    case '2':
      for (final Part part : this.selectedParts) {
        toggleSelection(part.middleJoint());
      }
      break;
    case '1':
      for (final Part part : this.selectedParts) {
        toggleSelection(part.bodyJoint());
      }
      break;
      
    // change the axis of rotation at current active joint
    case 'X':
    case 'x':
      this.selectedAxis = Axis.X;
      break;
    case 'Y':
    case 'y':
      this.selectedAxis = Axis.Y;
      break;
    case 'Z':
    case 'z':
      this.selectedAxis = Axis.Z;
      break;
    default:
      break;
    }
  }

  /**
   * Prints the joints on the System.out print stream.
   */
  private void printJoints() {
    this.printJoints(System.out);
  }

  /**
   * Prints the joints on the specified PrintStream.
   * 
   * @param printStream
   *          The stream on which to print each of the components.
   */
  private void printJoints(final PrintStream printStream) {
    for (final Component component : this.components) {
      printStream.println(component);
    }
  }

  /**
   * This method is intentionally unimplemented.
   * 
   * @param mouse
   *          This parameter is ignored.
   */
  public void mouseClicked(MouseEvent mouse) {
    // intentionally unimplemented
  }

  /**
   * Updates the rotation quaternion as the mouse is dragged.
   * 
   * @param mouse
   *          The mouse drag event object.
   */
  public void mouseDragged(final MouseEvent mouse) {
	if (this.rotate_world) {
		// get the current position of the mouse
		final int x = mouse.getX();
		final int y = mouse.getY();
	
		// get the change in position from the previous one
		final int dx = x - this.last_x;
		final int dy = y - this.last_y;
	
		// create a unit vector in the direction of the vector (dy, dx, 0)
		final double magnitude = Math.sqrt(dx * dx + dy * dy);
		final float[] axis = magnitude == 0 ? new float[]{1,0,0}: // avoid dividing by 0
			new float[] { (float) (dy / magnitude),(float) (dx / magnitude), 0 };
	
		// calculate appropriate quaternion
		final float viewing_delta = 3.1415927f / 180.0f;
		final float s = (float) Math.sin(0.5f * viewing_delta);
		final float c = (float) Math.cos(0.5f * viewing_delta);
		final Quaternion Q = new Quaternion(c, s * axis[0], s * axis[1], s
				* axis[2]);
		this.viewing_quaternion = Q.multiply(this.viewing_quaternion);
	
		// normalize to counteract accumulating round-off error
		this.viewing_quaternion.normalize();
	
		// save x, y as last x, y
		this.last_x = x;
		this.last_y = y;
	}
  }

  /**
   * This method is intentionally unimplemented.
   * 
   * @param mouse
   *          This parameter is ignored.
   */
  public void mouseEntered(MouseEvent mouse) {
    // intentionally unimplemented
  }

  /**
   * This method is intentionally unimplemented.
   * 
   * @param mouse
   *          This parameter is ignored.
   */
  public void mouseExited(MouseEvent mouse) {
    // intentionally unimplemented
  }

  /**
   * This method is intentionally unimplemented.
   * 
   * @param mouse
   *          This parameter is ignored.
   */
  public void mouseMoved(MouseEvent mouse) {
    // intentionally unimplemented
  }

  /**
   * Starts rotating the world if the left mouse button was released.
   * 
   * @param mouse
   *          The mouse press event object.
   */
  public void mousePressed(final MouseEvent mouse) {
    if (mouse.getButton() == MouseEvent.BUTTON1) {
      this.last_x = mouse.getX();
      this.last_y = mouse.getY();
      this.rotate_world = true;
    }
  }

  /**
   * Stops rotating the world if the left mouse button was released.
   * 
   * @param mouse
   *          The mouse release event object.
   */
  public void mouseReleased(final MouseEvent mouse) {
    if (mouse.getButton() == MouseEvent.BUTTON1) {
      this.rotate_world = false;
    }
  }

  /**
   * {@inheritDoc}
   * 
   * @param drawable
   *          {@inheritDoc}
   * @param x
   *          {@inheritDoc}
   * @param y
   *          {@inheritDoc}
   * @param width
   *          {@inheritDoc}
   * @param height
   *          {@inheritDoc}
   */
  public void reshape(final GLAutoDrawable drawable, final int x, final int y,
      final int width, final int height) {
    final GL2 gl = (GL2)drawable.getGL();

    // prevent division by zero by ensuring window has height 1 at least
    final int newHeight = Math.max(1, height);

    // compute the aspect ratio
    final double ratio = (double) width / newHeight;

    // reset the projection coordinate system before modifying it
    gl.glMatrixMode(GL2.GL_PROJECTION);
    gl.glLoadIdentity();

    // set the viewport to be the entire window
    gl.glViewport(0, 0, width, newHeight);

    // set the clipping volume
    this.glu.gluPerspective(25, ratio, 0.1, 100);

    // camera positioned at (0,0,6), look at point (0,0,0), up vector (0,1,0)
    this.glu.gluLookAt(0, 0, 12, 0, 0, 0, 0, 1, 0);

    // switch back to model coordinate system
    gl.glMatrixMode(GL2.GL_MODELVIEW);
  }

  private void toggleSelection(final Component component) {
    if (this.selectedComponents.contains(component)) {
      this.selectedComponents.remove(component);
      component.setColor(INACTIVE_COLOR);
    } else {
      this.selectedComponents.add(component);
      component.setColor(ACTIVE_COLOR);
    }
    this.stateChanged = true;
  }

  private void toggleSelection(final Part part) {
    if (this.selectedParts.contains(part)) {
      this.selectedParts.remove(part);
      this.selectedComponents.removeAll(part.joints());
      for (final Component joint : part.joints()) {
        joint.setColor(INACTIVE_COLOR);
      }
    } else {
      this.selectedParts.add(part);
    }
    this.stateChanged = true;
  }

@Override
public void dispose(GLAutoDrawable drawable) {
	// TODO Auto-generated method stub
	
}
}
