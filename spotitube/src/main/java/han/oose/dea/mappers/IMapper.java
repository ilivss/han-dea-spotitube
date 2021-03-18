package han.oose.dea.mappers;

public interface IMapper<T, K> {
    T toDTO(K domainObject);
    K fromDTO (T DTOObject);
}
