package quantitymeasurement.model;

// Arithmetic Enum
public enum ArithmeticOperation {
	
	ADD{
		@Override
		public double compute(double thisBase, double otherBase) {
			return thisBase+otherBase;
		}
	},
	
	SUBTRACT{
		@Override
		public double compute(double thisBase, double otherBase) {
			return thisBase-otherBase;
		}
	},
	
	DIVIDE{
		@Override
		public double compute(double thisBase, double otherBase) {
			if(otherBase == 0.0) {
				throw new ArithmeticException("Cannot divide by zero");
			}
			return thisBase/otherBase;
		}
	};
	
	public abstract double compute(double thisBase, double otherBase);
	
//	private double performArithmetic(Quantity<U> other, U targetUnit, ArithmeticOperation operatoin) {
//		ADD((a,b) -> a+b),
//		SUBTRACT((a,b)->a-b),
//		DIVIDE((a,b) ->{
//			if(b==0.0) throw new ArithmaticException("Divide by zero");
//			return a/b;
//		});
//	}
//	private enum ArithmeticOperation{
//		ADD((a,b) -> a+b),
//		SUBTRACT((a,b) -> a-b),
//		DIVIDE((a,b) ->{
//			if(b == 0.0) throw new ArithmeticException("Cannot divide by zero");
//			return a/b;
//		});
//		
//		private final DoubleBinaryOperation operation;
//		
//		ArithmeticOperation(DoubleBinaryOperation operation){
//			this.operation = operation;
//		}
//		
//		public double compute(double a, double b) {
//			return operation.applyAsDouble(a,b);
//		}
//		
//	}
	
}
