package org.example.db;

import org.example.helpers.DbHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DeviceServiceRepository {
    public void create(DeviceServiceEntity entity) {
        try (Statement statement = DbHelper.getInstance().getConnection().createStatement()) {
            statement.execute(
                    "INSERT INTO device_service(owner_name, status) VALUES ('" + entity.getOwnerName() + "', '" + entity.getStatus().ordinal() + "')"
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(DeviceServiceEntity entity) {
        Integer id = entity.getId();

        try (Statement statement = DbHelper.getInstance().getConnection().createStatement()) {
            statement.executeUpdate(
                    "UPDATE device_service SET " + buildUpdateValues(entity) + " WHERE id = " + id
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<DeviceServiceEntity> getAll() {
        try (Statement statement = DbHelper.getInstance().getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM device_service");

            List<DeviceServiceEntity> entities = new ArrayList<>();
            while (resultSet.next()) {
                int status = resultSet.getInt("status");
                int decision = resultSet.getInt("decision");

                entities.add(
                        DeviceServiceEntity.builder()
                                .id(resultSet.getInt("id"))
                                .ownerName(resultSet.getString("owner_name"))
                                .decision(Decision.values()[decision])
                                .status(Status.values()[status])
                                .build()
                );
            }
            return entities;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // todo: replace with proper where in db
    public DeviceServiceEntity getById(Integer id) {
        return getAll().stream().filter(entity -> Objects.equals(entity.getId(), id)).findFirst()
                .orElseThrow(() -> new RuntimeException("Not found id: " + id));
    }

    private String buildUpdateValues(DeviceServiceEntity deviceService) {
        StringBuilder builder = new StringBuilder();

        if (deviceService.getOwnerName() != null) {
            builder.append("owner_name='" + deviceService.getOwnerName() + "'");
        }
        if (deviceService.getStatus() != null) {
            builder.append("status=" + deviceService.getStatus().ordinal());
        }
        if (deviceService.getDecision() != null) {
            builder.append("decision=" + deviceService.getDecision().ordinal());
        }

        return builder.toString();
    }
}
