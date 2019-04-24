package usuario.app.sistemadecadastro.View;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import usuario.app.sistemadecadastro.R;

public class TelaListagemUsuarios {
    MainActivity act;
    TelaPrincipal tela_principal;
    Button btanterior, btproximo, btfechar, btexcluir, btalterar;
    TextView txtnome, txttelefone, txtendereco, txtstatus;

    int index = 0;
    public TelaListagemUsuarios(MainActivity act,TelaPrincipal tela_principal) {
        this.act = act;
        this.tela_principal = tela_principal;
    }
    public void CarregarTela()
    {
        if(act.getaRegistros().size() == 0)
        {
            (new AlertDialog.Builder(act))
                    .setTitle("Aviso")
                    .setMessage("Não existe nenhum registro cadastrado.")
                    .setNeutralButton("OK", null)
                    .show();
            return;
        }
        act.setContentView(R.layout.listagem_usuarios_cadastros);
        btanterior = (Button) act.findViewById(R.id.btanterior);
        btproximo = (Button) act.findViewById(R.id.btproximo);
        btfechar = (Button) act.findViewById(R.id.btfechar);
        btexcluir = (Button) act.findViewById(R.id.btexcluir);
        btalterar = (Button) act.findViewById(R.id.btalterar);
        txtnome = (TextView) act.findViewById(R.id.txtnome);
        txtendereco = (TextView) act.findViewById(R.id.txtendereco);
        txttelefone = (TextView) act.findViewById(R.id.txttelefone);
        txtstatus = (TextView) act.findViewById(R.id.txtstatus);
        index = 0;
        PreencheCampos(index);
        AtualizaStatus(index);

        btanterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(index > 0)
                {
                    index--;
                    PreencheCampos(index);
                    AtualizaStatus(index);
                }
            }
        });
        btproximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(index < act.getaRegistros().size() - 1)
                {
                    index++;
                    PreencheCampos(index);
                    AtualizaStatus(index);
                }
            }
        });
        btexcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogo = new AlertDialog.Builder(act);
                dialogo.setTitle("Aviso");
                dialogo.setMessage("Tem certeza em excluir o usuário " + txtnome.getText() + " ?");
                dialogo.setNegativeButton("Não", null);
                dialogo.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Excluir(index);
                    }});
                dialogo.show();
            }
        });

        btalterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act.tela_alteracao.CarregarTela();
                act.tela_alteracao.ednome.setText(txtnome.getText().toString());
                act.tela_alteracao.PesquisarRegistro(act.getaRegistros(),txtnome.getText().toString() );
                act.tela_alteracao.index = index;
                }
        });

        btfechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialogo = new
                        AlertDialog.Builder(act);
                dialogo.setTitle("Aviso");
                dialogo.setMessage("Sair da Listagem ?");
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
    private void PreencheCampos(int idx)
    {
        txtnome.setText(act.getaRegistros().get(idx).getNome());
        txttelefone.setText(act.getaRegistros().get(idx).getTelefone());
        txtendereco.setText(act.getaRegistros().get(idx).getEndereco());
    }
    private void AtualizaStatus(int idx)
    {
        int total = act.getaRegistros().size();
        txtstatus.setText("Registros : " + (idx+1) + "/" + total);
    }

    public void Excluir(int idx)
    {
        tela_principal.CarregarTela();
        act.getaRegistros().remove(idx);
        act.ExibirMensagem("Registro Excluído");
    }
}