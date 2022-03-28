import java.util.ArrayList;

public class Order {
	public int orderId;
	public String orderStatus;
	public ArrayList<FoodItem> cart;
	public double totalPrice;
	
	public Order() {
		this.orderStatus = "Order Received by Restaurant";
		this.cart = new ArrayList<FoodItem>();
		this.totalPrice = 0.00;
	}
	
	public int getOrderId() {
		return orderId;
	}
	
	public void setOrderId(int orderId) {
		
	}
	
	public String getOrderStatus() {
		return orderStatus;
	}
	
	public void setOrderStatus(String newOrderStatus) {
		orderStatus = newOrderStatus;
	}
	
	public boolean addToOrder(FoodItem foodItem) {
		boolean result = cart.add(foodItem);
		updatePrice();
		return result;
	}
	
	public boolean removeFromOrder(FoodItem foodItem) {
		boolean result = cart.remove(foodItem);
		updatePrice();
		return result;
	}
	
	public double getTotalPrice() {
		return totalPrice;
	}
	
	private void updatePrice() {
		// for each item in the cart, get the price
		totalPrice = 0.00;
		cart.forEach((foodItem) -> totalPrice += foodItem.getPrice());
	}
	
	public void printOrder() {
		System.out.print("Current order: {");
		cart.forEach((foodItem) -> System.out.print(foodItem.getName() + "(" + foodItem.getPrice() + ")" + ", "));
		System.out.println(" }");
	}
	
	
}
