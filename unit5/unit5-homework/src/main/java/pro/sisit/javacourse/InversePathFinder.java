package pro.sisit.javacourse;

import pro.sisit.javacourse.inverse.BigDecimalRange;
import pro.sisit.javacourse.inverse.InverseDeliveryTask;
import pro.sisit.javacourse.inverse.Solution;
import pro.sisit.javacourse.optimal.DeliveryTask;
import pro.sisit.javacourse.optimal.Route;
import pro.sisit.javacourse.optimal.RouteType;
import pro.sisit.javacourse.optimal.Transport;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class InversePathFinder {

    /**
     * Принимает на вход InverseDeliveryTask task - обратную задачу доставки груза.
     * Мы должны по переданному в ней списку возможных задач, списку доступного транспорта,
     * а также диапазону цены определить что за задачи доставки груза могли быть решены.
     * Возвращает список решений задачи доставки груза (Solution),
     * удовлетворяющий параметру переданному параметру task:
     * 1. Транспорт должен быть доступен для решения данной задачи
     * 2. Стоимость решения задачи должна входить в диапазон цены пераметра task
     * 3. Также возвращаемый список должен быть отсортирован по двум значениям:
     * - сначала по итоговой стоимости решения задачи - по убыванию
     * - затем, если цены одинаковы - по наименованию задачи доставки по алфавиту - по возрастанию
     * Если task равна null, то функция должна вернуть пустой список доступных решений.
     * Если внутри параметра task данные по возможным задачам, доступному транспорту либо по итоговой цене равны null,
     * то функция должна вернуть пустой список доступных решений.
     */
    public List<Solution> getAllSolutions(InverseDeliveryTask task) {

        List<Solution> solutions = new ArrayList<>();

        if (task == null) {
            return solutions;
        }
        if (task.getTasks() == null || task.getTransports() == null || task.getPriceRange() == null) {
            return solutions;
        }

        task.getTasks()
                .forEach(deliveryTask -> getAvailableTransports(deliveryTask, task.getTransports(), task.getPriceRange())
                        .forEach(
                                transport -> solutions.add(new Solution(deliveryTask, transport,
                                        getTransportPriceByDeliveryTask(deliveryTask, transport)))
                        ));

        return solutions.stream()
                .sorted(Comparator.comparing(Solution::getPrice).reversed().thenComparing(solution -> solution.getDeliveryTask().getName()))
                .collect(Collectors.toList());
    }

    private List<Transport> getAvailableTransports(DeliveryTask deliveryTask, List<Transport> transports, BigDecimalRange range) {
        return transports.stream()
                .filter(transport -> isTransportSuitableByRouteType(deliveryTask, transport))
                .filter(transport -> isTransportSuitableByVolume(transport.getVolume(), deliveryTask.getVolume()))
                .filter(transport -> BigDecimalRange.isPriceInPriceRange(getTransportPriceByDeliveryTask(deliveryTask, transport), range))
                .collect(Collectors.toList());
    }

    private boolean isTransportSuitableByVolume(BigDecimal transportVolume, BigDecimal deliveryTaskVolume) {
        return transportVolume.compareTo(deliveryTaskVolume) >= 0;
    }

    private boolean isTransportSuitableByRouteType(DeliveryTask deliveryTask, Transport transport) {
        return deliveryTask.getRoutes().stream().anyMatch(route -> route.getType().equals(transport.getType()));
    }

    private BigDecimal getTransportPriceByDeliveryTask(DeliveryTask deliveryTask, Transport transport) {
        return deliveryTask.getRoutes().stream()
                .filter(route -> route.getType().equals(transport.getType()))
                .findAny()
                .map(route -> route.getLength().multiply(transport.getPrice()))
                .orElse(null);
    }
}

