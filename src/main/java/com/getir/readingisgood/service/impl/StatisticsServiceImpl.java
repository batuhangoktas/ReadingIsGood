package com.getir.readingisgood.service.impl;

import com.getir.readingisgood.dto.StatisticsDto;
import com.getir.readingisgood.entity.Book;
import com.getir.readingisgood.entity.BookOrder;
import com.getir.readingisgood.entity.Order;
import com.getir.readingisgood.exception.CustomerIsNotFoundException;
import com.getir.readingisgood.repository.BookRepository;
import com.getir.readingisgood.repository.OrderRepository;
import com.getir.readingisgood.service.BookService;
import com.getir.readingisgood.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    BookService bookService;

    @Override
    public List<StatisticsDto> getMonthlyStatisticsByCustomer(String customerId) {
        List<Order> orderList = orderRepository.findAllByCustomerId(customerId);

        if(orderList.isEmpty())
                throw new CustomerIsNotFoundException("Customer is not found customerId: " + customerId);

        List<StatisticsDto> statisticsDtoList = new ArrayList<StatisticsDto>();
        for (int cnt = 0; cnt < orderList.size(); cnt++) {

            int totalBookPieceForOrder = 0;
            Double totalPurchasedAmountForOrder = 0.0;
            List<BookOrder> bookOrderList = orderList.get(cnt).getBookOrderList();
            for (int orderCnt = 0; orderCnt < bookOrderList.size(); orderCnt++) {
                String bookId = bookOrderList.get(orderCnt).getBookId();
                int piece = bookOrderList.get(orderCnt).getPiece();
                totalBookPieceForOrder += piece;
                Optional<Book> book = bookService.findById(bookId);
                totalPurchasedAmountForOrder += book.get().getPrice() * piece;
            }
            String month = orderList.get(cnt).getCreateDate().getMonth().toString();

            boolean exists = statisticsDtoList.stream().anyMatch(months -> months.getMonth().contains(month));
            if (exists) {
                for (int sumCnt = 0; sumCnt < statisticsDtoList.size(); sumCnt++) {
                    if (statisticsDtoList.get(sumCnt).getMonth().equals(month)) {
                        statisticsDtoList.get(sumCnt).setTotalOrderCount(statisticsDtoList.get(sumCnt).getTotalOrderCount() + 1);
                        statisticsDtoList.get(sumCnt).setTotalBookCount(statisticsDtoList.get(sumCnt).getTotalBookCount() + totalBookPieceForOrder);
                        statisticsDtoList.get(sumCnt).setTotalPurchasedAmount(statisticsDtoList.get(sumCnt).getTotalPurchasedAmount() + totalPurchasedAmountForOrder);
                    }
                }
            } else {
                StatisticsDto statisticsDto = StatisticsDto.builder()
                        .month(month)
                        .totalOrderCount(1)
                        .totalBookCount(totalBookPieceForOrder)
                        .totalPurchasedAmount(totalPurchasedAmountForOrder)
                        .build();
                statisticsDtoList.add(statisticsDto);
            }


        }
        return statisticsDtoList;
    }
}
