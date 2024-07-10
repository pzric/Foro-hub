package com.alurachallenge.Foro_hub.domain.usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.List;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @Column(unique = true)
    private String correo;
    private String contrasena;
    @Enumerated(EnumType.STRING)
    private Rol rol;

    public Usuario(DatosUsuario datosUsuario, PasswordEncoder passwordEncoder) {
        this.nombre = datosUsuario.nombre();
        this.correo = datosUsuario.correo();
        this.contrasena = passwordEncoder.encode(datosUsuario.contrasena());
        this.rol = datosUsuario.rol();
    }

    public void actualizarUsuario(ActualizarDatosUsuario actualizarDatosUsuario, PasswordEncoder passwordEncoder) {
        this.nombre =actualizarDatosUsuario.nombre();
        this.correo = actualizarDatosUsuario.correo();
        this.contrasena = passwordEncoder.encode(actualizarDatosUsuario.contrasena());
        this.rol = actualizarDatosUsuario.rol();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return contrasena;
    }

    @Override
    public String getUsername() {
        return correo;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}

