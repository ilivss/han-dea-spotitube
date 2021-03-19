package han.oose.dea.rest.mappers;

public interface IRestMapper<DTO, DO> {
    DTO toDTO(DO domainObject);
    DO toDomainObject(DTO DTOObject);
}
