package pro.sisit.javacourse;

import pro.sisit.javacourse.optimal.DeliveryTask;
import pro.sisit.javacourse.optimal.Route;
import pro.sisit.javacourse.optimal.RouteType;
import pro.sisit.javacourse.optimal.Transport;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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

        Optional<DeliveryTask> optionalDeliveryTask = Optional.ofNullable(deliveryTask);
        Optional<List<Transport>> optionalTransports = Optional.ofNullable(transports);
        
        if (optionalDeliveryTask.isPresent() && optionalTransports.isPresent()){
            return transports.stream()
                    .filter(transportSuitableByRouteTypePredicate(deliveryTask))
                    .filter(transportSuitableByVolumePredicate(deliveryTask))
                    .min(Comparator.comparing(transport -> getTransportPriceByDeliveryTask(deliveryTask, transport)))
                    .orElse(null);
        }
        else return null;
    }

    private Predicate<Transport> transportSuitableByRouteTypePredicate(DeliveryTask deliveryTask) {
        return transport -> deliveryTask.getRoutes().stream().anyMatch(route -> route.getType().equals(transport.getType()));
    }

    private Predicate<Transport> transportSuitableByVolumePredicate(DeliveryTask deliveryTask) {
        return transport -> transport.getVolume().compareTo(deliveryTask.getVolume()) >= 0;
    }

    private BigDecimal getTransportPriceByDeliveryTask(DeliveryTask deliveryTask, Transport transport) {
        return deliveryTask.getRoutes().stream()
                .filter(route -> route.getType().equals(transport.getType()))
                .findAny()
                .map(route -> route.getLength().multiply(transport.getPrice()))
                .orElse(null);

    }
}
