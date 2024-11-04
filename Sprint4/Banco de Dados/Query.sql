-- Relatório utilizando classificação de dados.
SELECT cd_automovel, sq_placa, nm_marca_veiculo, nm_modelo_veiculo, dt_veiculo, 
CASE WHEN EXTRACT(YEAR FROM dt_veiculo) < 2000 THEN 'Antigo' WHEN EXTRACT(YEAR FROM dt_veiculo) BETWEEN 2000 AND 2015 THEN 'Usado' ELSE 'Recente'
END AS categoria FROM t_ps_automovel ORDER BY dt_veiculo;
    
SELECT nm_marca_veiculo, COUNT(*) AS quantidade FROM t_ps_automovel
GROUP BY nm_marca_veiculo ORDER BY quantidade DESC;
    
SELECT nm_marca_veiculo, nm_modelo_veiculo,
EXTRACT(YEAR FROM dt_veiculo) AS ano_fabricacao,sq_placa
FROM t_ps_automovel ORDER BY nm_marca_veiculo, ano_fabricacao;

-- Relatório utilizando alguma função do tipo numérica simples.
SELECT nm_marca_veiculo,COUNT(*) AS total_automoveis
FROM t_ps_automovel
GROUP BY nm_marca_veiculo
ORDER BY total_automoveis DESC;

SELECT AVG(EXTRACT(YEAR FROM dt_veiculo)) AS media_ano_fabricacao
FROM t_ps_automovel;

-- Relatório utilizando alguma função de grupo.
SELECT nm_modelo_veiculo, COUNT(*) AS total_automoveis
FROM t_ps_automovel
GROUP BY nm_modelo_veiculo
ORDER BY total_automoveis DESC;

SELECT nm_modelo_veiculo, AVG(cd_automovel) AS media_preco
FROM  t_ps_automovel
GROUP BY nm_modelo_veiculo
ORDER BY media_preco DESC;

-- Relatório utilizando sub consulta. 
SELECT p.nm_produto, p.vl_produto, (SELECT COUNT(o.cd_orcamento) 
FROM t_ps_orcamento o WHERE o.st_orcamento = 'I' 
       AND EXISTS (
           SELECT 1 
           FROM t_ps_itens_orcamento io 
           WHERE io.cd_orcamento = o.cd_orcamento 
             AND io.cd_produto = p.cd_produto
       )
    ) AS total_orcamentos_pendentes FROM 
    t_ps_produto p WHERE 
    EXISTS (
        SELECT 1 
        FROM t_ps_itens_orcamento io 
        WHERE io.cd_produto = p.cd_produto
    );

SELECT
    a.sq_placa,
    a.nm_marca_veiculo,
    a.nm_modelo_veiculo,
    (SELECT d.nm_descricao_diagnostico FROM t_ps_diagnostico d 
     WHERE d.cd_automovel = a.cd_automovel 
     AND d.st_diagnostico = 'A'  
     ORDER BY 
         d.dt_inicio_diagnostico DESC
     FETCH FIRST 1 ROWS ONLY) AS ultimo_diagnostico,
    (SELECT o.sq_cnpj FROM t_ps_oficina o 
     JOIN t_ps_diagnostico d ON o.cd_oficina = d.cd_oficina 
     WHERE 
         d.cd_automovel = a.cd_automovel 
     AND d.st_diagnostico = 'A' 
FETCH FIRST 1 ROWS ONLY) AS oficina_atual FROM t_ps_automovel a;

-- Relatório utilizando junção de tabelas.
SELECT 
    a.sq_placa,
    a.nm_marca_veiculo,
    a.nm_modelo_veiculo,
    o.sq_cnpj,
    d.dt_inicio_diagnostico
FROM t_ps_automovel a JOIN t_ps_diagnostico d ON a.cd_automovel = d.cd_automovel
JOIN t_ps_oficina o ON d.cd_oficina = o.cd_oficina;

SELECT
    a.sq_placa AS "Placa do Automóvel",
    a.nm_marca_veiculo AS "Marca do Veículo",
    a.nm_modelo_veiculo AS "Modelo do Veículo",
    d.nm_descricao_diagnostico AS "Descrição do Diagnóstico",
    d.dt_inicio_diagnostico AS "Data de Início do Diagnóstico",
    d.dt_fim_diagnostico AS "Data de Fim do Diagnóstico",
    o.sq_cnpj AS "CNPJ da Oficina",
    o.sq_inscricao_estadual AS "Inscrição Estadual da Oficina"
FROM t_ps_automovel a
JOIN t_ps_diagnostico d ON a.cd_automovel = d.cd_automovel
JOIN t_ps_oficina o ON d.cd_oficina = o.cd_oficina
WHERE d.st_diagnostico = 'A';  








