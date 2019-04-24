package usuario.app.sistemadecadastro.View;
import android.app.*;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import usuario.app.sistemadecadastro.R;
import usuario.app.sistemadecadastro.Model.Registro;

public class TelaCadastroUsuario {
    MainActivity act;
    EditText ednome,edendereco, edtelefone;
    Button btcadastrar, btcancelar_cadastro;
    TelaPrincipal tela_principal;
    public TelaCadastroUsuario(MainActivity act, TelaPrincipal tela_principal)
    {
        this.act = act;
        this.tela_principal = tela_principal;
    }
    public void CarregarTela()
    {
        act.setContentView(R.layout.cadastro_de_usuario);
        ednome = (EditText) act.findViewById(R.id.ednome);
        edtelefone = (EditText) act.findViewById(R.id.edtelefone);
        edendereco = (EditText) act.findViewById(R.id.edendereco);
        btcadastrar = (Button) act.findViewById(R.id.btcadastrar);
        btcancelar_cadastro = (Button) act.findViewById(R.id.btcancelar_cadastro);

        SimpleMaskFormatter SMTel = new SimpleMaskFormatter("(NN)NNNNN-NNNN");
        MaskTextWatcher maskTel = new MaskTextWatcher(edtelefone, SMTel);
        edtelefone.addTextChangedListener(maskTel);

        btcadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder dialogo = new AlertDialog.Builder(act);
                dialogo.setTitle("Aviso");
                dialogo.setMessage("Cadastrar usuário ?");
                dialogo.setNegativeButton("Não", null);
                dialogo.setPositiveButton("Sim", new
                        DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String nome = ednome.getText().toString();
                                String endereco = edendereco.getText().toString();
                                String telefone = edtelefone.getText().toString();

                                // O IF ELSE abaixo verifica se foi digitado algum valor nos campos, caso não seja digitado, cadastro
                                //não será realizado. Allan (Prof. me ajudou com o nome.length() )
                                if(nome.length() == 0 || endereco.length() == 0 || telefone.length() == 0){
                                    act.ExibirMensagem("Por favor, Preencha os Campos do Formulário.");
                                }else {
                                    act.getaRegistros().add(new Registro(nome, endereco, telefone));
                                    act.ExibirMensagem("Cadastro efetuado com sucesso.");
                                    tela_principal.CarregarTela();
                                }
                            }
                        });
                dialogo.show();
            }
        });
        btcancelar_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialogo = new
                        AlertDialog.Builder(act);
                dialogo.setTitle("Aviso");
                dialogo.setMessage("Sair do cadastro ?");
                dialogo.setNegativeButton("Não", null);
                dialogo.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tela_principal.CarregarTela();
                    }});
                dialogo.show();
            }
        });
    }
}