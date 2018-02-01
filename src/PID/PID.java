package PID;

//code heavily influenced by Techfire's code, frc 225

//sets the PID CLass
public class PID {
	//initiallizes the variables for the PID LOOP
	public double kP;
	double kI;
	double kD;
    double error;
    double target; 
    public double tolerance = 100;
    int loopsStable = 0;
    double errSum = 0;
    double maxInc = 0.01;
    double previousValue = 0;
    boolean targetChangedBeforeCalculate = true;
    double maxOutput=1, minOutput=-1;
    //allows diffrent P,I, and D's for diffent classes
    public PID(double kP, double kI, double kD)
    {
        this.kP = kP;
        this.kI = kI;
        this.kD = kD; 
    }
    /**
     * this sets the range of outputs of the pid loop
     * @param max maximum output
     * @param min minimum output 
     */
    //sets the limits for safe outputs 
    public void setOutputConstraints(double max, double min)
    {
        maxOutput = max;
        minOutput = min;
    }
    
    public double getTarget()
    {
        return target;
    }
    
    public void setMaxInc(double val)
    {
        this.maxInc = val;
    }
    
    public void setOkError(double okError)
    {
        this.tolerance = okError;
    }
    
    public boolean atTarget()
    {
        if ( Math.abs(getError()) <= tolerance )
        {
            loopsStable++;
        }
        else {
            loopsStable = 0;
        }
        return loopsStable > 3;
    }
    
    public void setP(double kP)
    {
        this.kP = kP;
    }
    
    public void setI(double kI)
    {
        this.kI = kI;
    }
    
    public void setD(double kD)
    {
        this.kD = kD;
    }
    
    public double getError()
    {
        if ( targetChangedBeforeCalculate ) {
            return Double.MAX_VALUE;
        }
        return error;
    }
    
    public void setTarget(double target)
    {
        targetChangedBeforeCalculate = true;
        this.target = target;
    }
    
    public double calculate(double input)
    {
        if ( targetChangedBeforeCalculate ) {
            targetChangedBeforeCalculate = false;
        }
        
        // P
        //proportional part of the pid loop
        error = target-input;
        
        // I
        if ( error >= tolerance ) {
            if ( errSum < 0 ) {
                errSum = 0;
            } if ( error < maxInc ) {
                errSum += error;
            }else {
                errSum += maxInc;
            }
        }else if ( error <= - tolerance ){
            if ( errSum < 0 ) {
                errSum = 0;
            }
            if ( errSum > -maxInc ) {
            	 errSum += error;
            } else {
                errSum -= maxInc;
            }
        }else {
            errSum = 0;
        }
        // D
        double velocity = input-previousValue;
        previousValue = input;
        
        double output = (kP*error)+(kI*errSum)-(kD*velocity);
        
        //if ( output > maxOutput )
          //  output = maxOutput;
        //else if ( output < minOutput )
          //  output = minOutput;
        return output;
        
    }
    
}
