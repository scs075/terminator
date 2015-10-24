package com.qualcomm.ftcrobotcontroller.opmodes.customCode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

public class Autonomous extends LinearOpMode {
    private DcMotor right;
    private DcMotor left;

    final static int ENCODER_CPR = 1440;    //encoder counts per revolution
    final static double GEAR_RATIO = 1;     //gear ratio
    final static double WHEEL_DIAMETER = 2.625;     //diameter of wheel
    final static double CIRCUMFERENCE = Math.PI * WHEEL_DIAMETER;
    private static double distance;
    final static double ROTATIONS = distance / CIRCUMFERENCE;
    final static double COUNTS = ENCODER_CPR * ROTATIONS * GEAR_RATIO;

    @Override
    public void runOpMode() throws InterruptedException {
        right = hardwareMap.dcMotor.get("Right");
        left = hardwareMap.dcMotor.get("Left");
        right.setDirection(DcMotor.Direction.REVERSE);

        right.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        left.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);

        distance = 12;
        right.setTargetPosition((int) COUNTS);
        left.setTargetPosition((int) COUNTS);

        right.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        left.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        right.setPower(.5);
        left.setPower(.5);
    }
}