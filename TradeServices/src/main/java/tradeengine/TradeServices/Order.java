package tradeengine.TradeServices;

public class Order {
        private String product;
        private int quantity;
        private float price;
        private String side;



        public Order(String product, int quantity, float price, String side) {
            this.product = product;
            this.quantity = quantity;
            this.price = price;
            this.side = side;
        }

        @Override
        public String toString() {
            return "Order{" +
                    "product='" + product + '\'' +
                    ", quantity=" + quantity +
                    ", price=" + price +
                    ", side='" + side +
                    '}';
        }

        public void setProduct(String product) {
            this.product = product;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public void setPrice(float price) {
            this.price = price;
        }

        public void setSide(String side) {
            this.side = side;
        }
    }

