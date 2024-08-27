package school.sptech.neosspringjava.api.dtos.phoneDto;

public record PhoneResponse(
        Integer id,
        String countryCode,
        String areaCode,
        String number
) {
}
