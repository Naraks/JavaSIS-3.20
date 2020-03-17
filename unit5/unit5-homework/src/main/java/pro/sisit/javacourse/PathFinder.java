package pro.sisit.javacourse;

import pro.sisit.javacourse.optimal.DeliveryTask;
import pro.sisit.javacourse.optimal.Route;
import pro.sisit.javacourse.optimal.RouteType;
import pro.sisit.javacourse.optimal.Transport;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PathFinder {

    /**
     * Возвращает оптимальный транспорт для переданной задачи.
     * Если deliveryTask равна null, то оптимальный транспорт тоже равен null.
     * Если список transports равен null, то оптимальеый транспорт тоже равен null.
     */
    public Transport getOptimalTransport(DeliveryTask deliveryTask, List<Transport> transports) {
        if (deliveryTask == null || transports == null) {
            return null;
        }

        List<RouteType> deliveryTaskRouteTypes =
                deliveryTask.getRoutes()
                        .stream()
                        .map(Route::getType)
                        .distinct()
                        .collect(Collectors.toList());
        return transports.stream()
                .filter(transport -> deliveryTaskRouteTypes.contains(transport.getType()))
                .filter(transport -> transport.getVolume().compareTo(deliveryTask.getVolume()) >= 0)
                .min(Comparator.comparing(Transport::getPrice))
                .get();
    }
}
