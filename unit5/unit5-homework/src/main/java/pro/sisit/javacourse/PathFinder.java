package pro.sisit.javacourse;

import pro.sisit.javacourse.optimal.DeliveryTask;
import pro.sisit.javacourse.optimal.Route;
import pro.sisit.javacourse.optimal.RouteType;
import pro.sisit.javacourse.optimal.Transport;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PathFinder {

    /**
     * Возвращает оптимальный транспорт для переданной задачи.
     * Если deliveryTask равна null, то оптимальный транспорт тоже равен null.
     * Если список transports равен null, то оптимальный транспорт тоже равен null.
     */
    public Transport getOptimalTransport(DeliveryTask deliveryTask, List<Transport> transports) {

        List<Transport> optionalTransports = Optional.ofNullable(transports).orElse(Collections.emptyList());

        return optionalTransports.stream()
                .filter(transport -> isTransportSuitableByRouteTypePredicate(deliveryTask, transport))
                .filter(transport -> isTransportSuitableByVolumePredicate(deliveryTask, transport))
                .min(Comparator.comparing(transport -> getTransportPriceByDeliveryTask(deliveryTask, transport)))
                .orElse(null);

    }

    private boolean isTransportSuitableByRouteTypePredicate(DeliveryTask deliveryTask, Transport transport) {
        return deliveryTask != null && deliveryTask.getRoutes().stream().anyMatch(route -> route.getType().equals(transport.getType()));
    }

    private boolean isTransportSuitableByVolumePredicate(DeliveryTask deliveryTask, Transport transport) {
        return deliveryTask != null && transport.getVolume().compareTo(deliveryTask.getVolume()) >= 0;
    }

    private BigDecimal getTransportPriceByDeliveryTask(DeliveryTask deliveryTask, Transport transport) {
        return deliveryTask.getRoutes().stream()
                .filter(route -> route.getType().equals(transport.getType()))
                .findAny()
                .map(route -> route.getLength().multiply(transport.getPrice()))
                .orElse(null);

    }
}
