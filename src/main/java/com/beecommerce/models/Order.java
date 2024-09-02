package com.beecommerce.models;

import lombok.*;
import org.springframework.data.neo4j.core.schema.*;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Node
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Order {
    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    private String id;
    @Relationship(type = "HAS_ORDER_DETAIL", direction = Relationship.Direction.OUTGOING)
    private List<OrderDetail> orderDetails;
    @Relationship(type = "HAS_ORDER", direction = Relationship.Direction.INCOMING)
    private Customer customer;
    private String status;
    private LocalDateTime orderDate;
    private String address;

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", orderDetails=" + orderDetails +
                ", customer=" + customer.getId() +
                ", status='" + status + '\'' +
                ", orderDate=" + orderDate +
                ", address='" + address + '\'' +
                '}';
    }


}
