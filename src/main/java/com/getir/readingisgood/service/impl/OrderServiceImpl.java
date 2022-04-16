package com.getir.readingisgood.service.impl;

import com.getir.readingisgood.dto.OrderDto;
import com.getir.readingisgood.entity.Book;
import com.getir.readingisgood.entity.BookOrder;
import com.getir.readingisgood.entity.Log;
import com.getir.readingisgood.entity.Order;
import com.getir.readingisgood.exception.BookIsNotFoundException;
import com.getir.readingisgood.exception.StockIsNotEnoughException;
import com.getir.readingisgood.repository.OrderRepository;
import com.getir.readingisgood.service.BookService;
import com.getir.readingisgood.service.LogService;
import com.getir.readingisgood.service.OrderService;
import com.getir.readingisgood.util.LogEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl extends BaseServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    BookService bookService;

    @Autowired
    LogService logService;

    public OrderServiceImpl() {
        super(OrderServiceImpl.class);
    }

    @Override
    public List<OrderDto> getCustomerOrderList(String customerId, Pageable pageable) {
        List<Order> orderList = orderRepository.findAllByCustomerId(customerId, pageable);
        return orderList.stream().map(OrderDto::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto create(OrderDto orderDto) {

        List<Book> bookList = new ArrayList<Book>();
        List<BookOrder> bookOrderList = orderDto.getBookOrderList();
        for (int cnt = 0; cnt < bookOrderList.size(); cnt++) {
            Optional<Book> book = bookService.findById(bookOrderList.get(cnt).getBookId());
            if (!book.isPresent())
                throw new BookIsNotFoundException("Book is not found bookId:" + bookOrderList.get(cnt).getBookId());
            bookList.add(book.get());
            if (book.get().getStock() < bookOrderList.get(cnt).getPiece()) {
                logger.info("Stock is not enough bookId:" + book.get().getId());
                throw new StockIsNotEnoughException("Stock is not enough, bookId:" + book.get().getId());
            }
        }
        Order order = Order.builder()
                .id(UUID.randomUUID().toString())
                .customerId(orderDto.getCustomerId())
                .bookOrderList(orderDto.getBookOrderList())
                .createDate(LocalDateTime.now())
                .build();

        orderRepository.save(order);
        logService.save(new Log(Order.class, order.getId(), LogEnum.Create));
        logger.info("Order received orderId:" + order.getId());
        orderDto.setId(order.getId());

        for (int cnt = 0; cnt < bookOrderList.size(); cnt++) {
            String orderBookId = bookOrderList.get(cnt).getBookId();
            Book book = bookList.stream().filter(books -> books.getId().equals(orderBookId)).collect(Collectors.toList()).get(0);

            book.setStock(book.getStock() - bookOrderList.get(cnt).getPiece());

            bookService.save(book);
            logService.save(new Log(Book.class, book.getId(), LogEnum.Update));
        }
        return orderDto;
    }

    @Override
    public OrderDto getOrderDetail(String orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        return OrderDto.toDto(order.get());
    }

    @Override
    public List<OrderDto> getOrderListBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        List<Order> orderList = orderRepository.findAllByCreateDateBetween(startDate, endDate);
        return orderList.stream().map(OrderDto::toDto)
                .collect(Collectors.toList());
    }
}
