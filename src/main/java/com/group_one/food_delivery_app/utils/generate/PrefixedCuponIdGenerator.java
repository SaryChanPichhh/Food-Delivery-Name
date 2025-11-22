package com.group_one.food_delivery_app.utils.generate;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

public class PrefixedCuponIdGenerator implements IdentifierGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object obj) {

        String prefix = "";

        // Query the max numeric part
        String query = "SELECT MAX(SUBSTRING(id, 4)) FROM Customer";
        Integer lastId = (Integer) session.createNativeQuery(query).getSingleResult();

        int nextId = (lastId == null ? 1 : lastId + 1);
        return prefix + String.format("%05d", nextId); // -> CUS00001
    }
}
