package pe.bn.com.sate.ope.persistence.mapper.external;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import pe.bn.com.sate.ope.transversal.dto.tablas.Agencia;

public interface AgenciaMapper {

	@Select("select f01.F01_COFICINA as B09_CODIGO, f01.F01_AOFICINA as B09_DESCRIPCION, "
			+ "f02.F02_CUBIGEO_DT as B09_COD_DEPARTAMENTO, "
			+ "f02.F02_CUBIGEO_PV as B09_COD_PROVINCIA, "
			+ "f02.F02_CUBIGEO_DT as B09_COD_DISTRITO, "
			+ "f01.F01_TOFICINA,f02.F02_DLOCAL as B09_DIRECCION "
			+ "from [BN_Tablas].[dbo].[BNMAF01] f01 "
			+ "inner join [BN_Tablas].[dbo].[BNMAF02] f02 on f01.F01_COFICINA = f02.F02_COFICINA "
			+ "where f01.F01_BOFICINA = '0' and f01.F01_COFICINA !='0000' "
			+ "and f02.F02_CUBIGEO_DP=#{codDepartamento} and f02.F02_CUBIGEO_PV=#{codProvincia} and f02.F02_CUBIGEO_DT=#{codDistrito}")
	@ResultMap("mapAgencia")
	public List<Agencia> buscarAgenciasPorUbigeo(
			@Param("codDepartamento") String codDepartamento,
			@Param("codProvincia") String codProvincia,
			@Param("codDistrito") String codDistrito);

	@Select("select distinct f01.F01_COFICINA as B09_CODIGO,f02.F02_DLOCAL as B09_DIRECCION, f01.F01_AOFICINA as B09_DESCRIPCION, "
			+ "f02.F02_CUBIGEO_DT as B09_COD_DEPARTAMENTO,f02.F02_CUBIGEO_PV as B09_COD_PROVINCIA, "
			+ "f02.F02_CUBIGEO_DT as B09_COD_DISTRITO "
			+ "from [BN_Tablas].[dbo].[BNMAF01] f01 "
			+ "inner join [BN_Tablas].[dbo].[BNMAF02] f02 on f01.F01_COFICINA = f02.F02_COFICINA "
			+ "where f01.F01_BOFICINA = '0' and f01.F01_COFICINA =#{codAgencia}")
	@ResultMap("mapAgencia")
	public List<Agencia> buscarAgenciaPorCodAgencia(
			@Param("codAgencia") String codAgencia);
}
