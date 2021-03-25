package han.oose.dea.persistence.mappers;

public interface IDataMapper <ResultSet, DO> {
    DO toDomainObject (ResultSet resultSet);
}
