package usuario.app.sistemadecadastro.View;
import android.view.View;
import android.widget.Button;

import usuario.app.sistemadecadastro.Model.DatabaseHelper;
import usuario.app.sistemadecadastro.Model.Registro;
import usuario.app.sistemadecadastro.R;

public class TelaPrincipal {
    MainActivity act;
    Button btcadastrar_usuario;
    Button bt_listar_usuarios_cadastrados;
    Button btalterar_usuario;
    Button btexcluir_usuario;
    TelaCadastroUsuario tela_cadastro;
    TelaListagemUsuarios tela_listagem;
    TelaAlterarCadastro tela_alteracao;
    TelaExcluirCadastro tela_exclusao;

    public TelaPrincipal(MainActivity act) {
        this.act = act;
    }
    public void CarregarTela()
    {
        act.setContentView(R.layout.tela_principal);
        btcadastrar_usuario = (Button) act.findViewById(R.id.btcadastrar_usuario);
        bt_listar_usuarios_cadastrados = (Button) act.findViewById(R.id.bt_listar_usuarios_cadastrados);
        btalterar_usuario = (Button) act.findViewById(R.id.bt_alterar_usuarios_cadastrados);
        btexcluir_usuario = (Button) act.findViewById(R.id.bt_excluir_usuarios_cadastrados);


        btcadastrar_usuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tela_cadastro.CarregarTela();
            }
        });
        bt_listar_usuarios_cadastrados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tela_listagem.CarregarTela();
            }
        });
        btalterar_usuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tela_alteracao.CarregarTela();
            }
        });
        btexcluir_usuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tela_exclusao.CarregarTela();
            }
        });

    }
    public void setTelaCadastro(TelaCadastroUsuario tela_cadastro)
    {
        this.tela_cadastro = tela_cadastro;
    }
    public void setTelaListagem(TelaListagemUsuarios tela_listagem)
    {
        this.tela_listagem = tela_listagem;
    }
    public void setTelaAlteracao(TelaAlterarCadastro tela_alteracao)
    {
        this.tela_alteracao = tela_alteracao;
    }
    public void setTelaExclusao(TelaExcluirCadastro tela_exclusao)
    {
        this.tela_exclusao = tela_exclusao;
    }
}