package com.kodilla.ecommercee.mapper;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.CartDto;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartMapper {

    public CartDto mapToCartDTO(final Cart cart) {
        return new CartDto(cart.getCartId(),
                cart.getOrder(),
                cart.getValue());
    }

    public Cart mapToCart(final CartDto cartDto) {
        return new Cart(cartDto.getCartId(),
                cartDto.getOrder(),
                cartDto.getValue());
    }

    public List<CartDto> mapToCartDtoList(final List<Cart> cartList) {
        return cartList.stream()
                .map(this::mapToCartDTO)
                .collect(Collectors.toList());
    }

}
